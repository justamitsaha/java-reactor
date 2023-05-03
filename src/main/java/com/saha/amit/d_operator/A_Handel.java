package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class A_Handel {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if (integer % 2 == 0) {
                        synchronousSink.next(integer);
                    } else {
                        synchronousSink.next("Odd Number" + integer);
                    }
                }).subscribe(Util.onNext());

        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
        }).map(Object::toString).handle((country, synchronousSink) -> {
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("India"))
                synchronousSink.complete();
        }).subscribe(Util.onNext());
    }
}
