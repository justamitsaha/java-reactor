package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class D_FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            do {
                synchronousSink.next(country);
            } while (country.equalsIgnoreCase("INDIA"));
            synchronousSink.complete();
        }).log().take(3).log().subscribe(Util.onNext());
    }
}
