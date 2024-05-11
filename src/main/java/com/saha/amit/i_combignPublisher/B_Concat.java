package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class B_Concat {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.range(1, 3)
                .delayElements(Duration.ofSeconds(1))
                .map(s -> Util.faker().howIMetYourMother().catchPhrase());
        Flux<String> flux2 = Flux.just("c", "d");
        Flux<String> flux3 = Flux.error(new RuntimeException("LOL"));

//        Flux<String> flux = flux1.concatWith(flux2);        // Combining flux 1 with 2 it will maintain order so after flux1, flux 2 will emit even though flux 2 is ready before 1
//        flux.subscribe(Util.subscriber("Amit -->"));

//        Flux<String> flux4 = Flux.concat(flux1,flux3,flux2);    // Combining flux 1,2 and 3 using factory method due to error in flux3 won't see flux2
//        flux4.subscribe(Util.subscriber("Shivangi-->"));

        Flux<String > flux5 = Flux.concatDelayError(flux1, flux3, flux2);   // Even tough there is error still flux2 will emit in fact it will emit before 3, even tough
        //flux3 is mentioned before
        flux5.subscribe(Util.subscriber("Shamit-->"));

        Util.sleepSeconds(10);

    }
}
