package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*
Generate uses synchronous Sink . Synchronous sink will repeat the logic in loop,
and we can emit only once per loop.
If we try to emit more than once it will throw error.
Also, we don't have to worry about cancelling subscription if we say take(2)
subscription will be cancelled after 2
*/
public class F_FluxGenerate {
    public static void main(String[] args) {
        generateDemoTermination();
    }

    public static void generateDemo(){
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    // synchronousSink.next(country);       // Will throw IllegalStateException: More than one call to onNext
                    System.out.println("emitting " + country);
                    if (country.equalsIgnoreCase("INDIA"))
                        synchronousSink.complete();
                })
                .take(2)
                .subscribe(Util.onNext());
    }

    public static void generateDemoTermination(){
        Flux.<String>generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    System.out.println("emitting " + country);
                })
                .takeUntil(country -> country.equalsIgnoreCase("INDIA"))  // This will control
                .subscribe(Util.onNext());
    }
}
