package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class C_TakeOperator {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for(int i =0; i<5; i++){
                fluxSink.next("hello "+i);
                System.out.println(i);      // Even if reciever takes 3 the source will continue to execute
            }
        })
            .take(3)
            .subscribe(s -> System.out.println("Flux 1 -->"+ s));

        //Problem with create is that it will continue to emit even subscription is cancelled,
        so we have to add a condition for fluxSink.isCancelled()

        Flux.create(fluxSink -> {
            String country = "";
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
                System.out.println("Flux 2 send-->"+ country)
            } while (country.equalsIgnoreCase("INDIA") && fluxSink.isCancelled());
            fluxSink.complete();
        })
                .take(3)
                .subscribe(s -> System.out.println("Flux 2 recieved-->"+ s));
    }
}
