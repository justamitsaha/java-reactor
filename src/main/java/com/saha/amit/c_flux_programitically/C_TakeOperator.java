package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class C_TakeOperator {
    public static void main(String[] args) {
        Flux.range(1, 10).log().take(3).log()
                .subscribe(System.out::println);

        //Problem with create is that it will continue to emit even subscription is cancelled

        Flux.create(fluxSink -> {
            String country = Util.faker().country().name();
            do {
                fluxSink.next(country);
            } while (country.equalsIgnoreCase("INDIA") && fluxSink.isCancelled());
            fluxSink.complete();
        })
                .log()
                .take(3)
                .log()
                .subscribe(Util.onNext());
    }
}
