package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class A_FluxCreateSink {
    public static void main(String[] args) {

        //It accepts a consumer of Flux Sink
        Flux.create(fluxSink -> {
            fluxSink.next(1);           //Items emitted with Next
            fluxSink.next(2);
            fluxSink.next(3);
        }).subscribe(System.out::println);

        Flux.create(fluxSink -> {
            String countryName = "";
            int count = 1;
            do {
                countryName = Util.faker().country().name();
                fluxSink.next(count + "--> " + countryName);
                count++;
            } while (!countryName.trim().equalsIgnoreCase("india"));
            fluxSink.complete();            // send complete signal when india is received
        }).subscribe(System.out::println);


    }
}

