package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class A_Repeat {

    public static void main(String[] args) {
        getInteger()
                .repeat(2)                  //After complete will repeat 2 times
                .subscribe(Util.subscriber());
    }

    private static Flux<String> getInteger() {
        return Flux.just(Util.faker().howIMetYourMother().catchPhrase())
                .doOnSubscribe(s -> System.out.println("Subscribed "))
                .doOnComplete(() -> System.out.println("Completed"));

    }
}
