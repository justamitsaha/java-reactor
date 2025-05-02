package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*
Handle is like filter + Map . It takes 2 param one the item and a synchronous sink
We can manipulate the item we are receiving and can emit again.
 */
public class A_Handel {
    public static void main(String[] args) {
        handleCountry();
    }

    public static void handleNumbers() {
        Flux.range(1, 20)
                .handle((number, synchronousSink) -> {      //item and Sink
                    if (number % 2 == 0) {                  //Filter
                        synchronousSink.next(number);
                    } else {
                        synchronousSink.next("Odd Number" + number);    //Map
                    }
                })
                .subscribe(Util.onNext());
    }

    public static void handleCountry() {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(Util.faker().country().name());
                })
                .map(Object::toString)
                .handle((country, synchronousSink) -> {
                    switch (country.toUpperCase()) {
                        case "INDIA" -> {
                            synchronousSink.next("I ♥ " + country);
                            synchronousSink.complete();
                        }
                        case "PAKISTAN" -> synchronousSink.next("Screw " + country);
                        default -> synchronousSink.next("I ♥ " + country);
                    }
                })
                .subscribe(Util.onNext());
    }
}
