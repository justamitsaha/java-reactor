package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class B_Concat {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");
        Flux<String> flux3 = Flux.error(new RuntimeException("LOL"));

        Flux<String> flux = flux1.concatWith(flux2);
        flux.subscribe(Util.subscriber());
        Flux<String> flux4 = Flux.concat(flux1,flux3,flux2);
        flux4.subscribe(Util.subscriber());
        Flux<String > flux5 = Flux.concatDelayError(flux1, flux3, flux2);
        flux5.subscribe(Util.subscriber());

    }
}
