package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class E_TakeOperator {
    public static void main(String[] args) {
        takeUntil();
    }

    public static void takeOperator() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(integer -> System.out.println(integer));
    }

    public static void takeWhile() {
        Flux.range(1, 10)
                .takeWhile(i -> i < 5)        // stop when condition is not met 1-4
                .subscribe(integer -> System.out.println(integer));
    }

    public static void takeUntil() {
        Flux.range(1, 10)
                .takeUntil(i -> i < 5)         // stop when condition is not met also allow last item
                .subscribe(integer -> System.out.println(integer));
    }

    //Even if receiver takes 3 the source will continue to execute.
    public static void createIssue() {
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next("hello " + i);
                        System.out.println(i);
                    }
                })
                .take(3)
                .subscribe(s -> System.out.println("Flux 1 -->" + s));
    }

    //he Problem with create method is that it, will continue to emit even subscription
    // is cancelled, so we have to add a condition for fluxSink.isCancelled()
    public static void createIssueFix(){
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













