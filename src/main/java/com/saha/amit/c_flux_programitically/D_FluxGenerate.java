package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class D_FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            // more than one call in generate won't work, it executes same value again and again
            //synchronousSink.next(country);
            if (country.equalsIgnoreCase("INDIA"))
                synchronousSink.complete();
        }).subscribe(Util.onNext());
    }
}
