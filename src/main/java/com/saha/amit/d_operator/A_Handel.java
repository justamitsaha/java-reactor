package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*
Handle is like filter + Map . It takes 2 param one the item and a synchronous sink
We can manipulate the item we are receiving and can emit again.
 */
public class A_Handel {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .handle((number, synchronousSink) -> {
                    if (number % 2 == 0) {
                        synchronousSink.next(number);
                    } else {
                        synchronousSink.next("Odd Number" + number);
                    }
                }).subscribe(Util.onNext());

        Flux.generate(synchronousSink -> {
                    synchronousSink.next(Util.faker().country().name());
                })
                .map(Object::toString)
                .handle((country, synchronousSink) -> {
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("India"))
                        synchronousSink.complete();
                })
                .subscribe(Util.onNext());
    }
}
