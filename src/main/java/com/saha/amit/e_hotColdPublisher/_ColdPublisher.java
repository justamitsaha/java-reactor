package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class _ColdPublisher {

    public static void main(String[] args) {
        demo1();

    }

    public static void demo1(){
        AtomicInteger integer = new AtomicInteger(0);

        Flux<Integer> flux = Flux.create(atomicIntegerFluxSink -> {
            System.out.println("invoked");
            for (int i=0; i<3; i++){
                atomicIntegerFluxSink.next(integer.getAndIncrement());
            }
            atomicIntegerFluxSink.complete();
        });

        //both flux receive different values
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }


}
