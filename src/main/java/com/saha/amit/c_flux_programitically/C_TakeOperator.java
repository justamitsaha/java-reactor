package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*Take operator takes int as a parameter and subscriber gets only those elements irrespective of how any elements are emitted
Here, we will see that even though subscriber is taking few elements but with flux create it still continues emitting items which we should avoid
*  */
public class C_TakeOperator {
    public static void main(String[] args) {

        // Even if receiver takes 3 the source will continue to execute
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next("hello " + i);
                        System.out.println(i);
                    }
                })
                .take(3)
                .subscribe(s -> System.out.println("Flux 1 -->" + s));

        /*The Problem with create method is that it will continue to emit even subscription is cancelled,
        so we have to add a condition for fluxSink.isCancelled()*/

        Flux.create(fluxSink -> {
                    String country = "";
                    do {
                        country = Util.faker().country().name();
                        fluxSink.next(country);
                        System.out.println("Flux 2 send-->" + country);
                    } while (!country.equalsIgnoreCase("INDIA") && !fluxSink.isCancelled());
                    fluxSink.complete();
                })
                .take(3)
                .subscribe(s -> System.out.println("Flux 2 received-->" + s));
    }
}













