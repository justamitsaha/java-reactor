package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class A_Repeat {

    public static void main(String[] args) {
        getInteger()
                .repeat(2)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getInteger(){
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("Subscribed 1"))
                .doOnComplete(() -> System.out.println("Completed 2"));

    }
}
