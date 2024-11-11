package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class _ColdPublisher {

    public static void main(String[] args) {

        AtomicInteger integer = new AtomicInteger(0);

        Flux<Integer> flux = Flux.create(atomicIntegerFluxSink -> {
            System.out.println("invoked");
            for (int i=0; i<3; i++){
                atomicIntegerFluxSink.next(integer.getAndIncrement());
            }
            atomicIntegerFluxSink.complete();
        });

        //both flux receive different values
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);


        //Different values not cold
        Flux<String> flux1 = Flux.create(stringFluxSink -> {
            for (int i=0; i<3; i++){
                stringFluxSink.next(Util.faker().funnyName().name());
            }
            stringFluxSink.complete();
        });

        flux1.delayElements(Duration.ofMillis(500)).subscribe(System.out::println);
        flux1.delayElements(Duration.ofMillis(500)).subscribe(System.out::println);

        Util.sleepSeconds(10);
    }
}
