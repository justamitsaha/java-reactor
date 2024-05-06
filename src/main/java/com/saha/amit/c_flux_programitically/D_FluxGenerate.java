package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*
Genertae uses synchronous Sink . Syncronous sink will repeate the logic in loop 
and we can emit only once per loop.
If we try to emit more than once it will throw error.
Also we don't have to worry about cancelling subscription if we say take(2) 
subscription will be cancelled after 2
*/
public class D_FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            // more than one call in generate won't work, it executes same value again and again
            //synchronousSink.next(country);
            if (country.equalsIgnoreCase("INDIA"))
                synchronousSink.complete();
        })
        .take(2)
        .subscribe(Util.onNext());
    }
}
