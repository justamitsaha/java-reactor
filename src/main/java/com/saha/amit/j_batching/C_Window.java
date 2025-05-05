package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
/*
Batch returns List Window return Flux
 */
public class C_Window {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        eventStream()
                .window(3)                                  //We call using window which returns a flux
                .flatMap(flux -> saveEvents(flux))              //Since we are getting flux we use flatMap and send to save events
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event"+i);
    }

    private static Mono<Integer> saveEvents(Flux<String> flux){
        return flux
                .doOnNext(e -> System.out.println("saving " + e))
                .doOnComplete(() -> {
                    System.out.println("saved this batch");
                    System.out.println("-------------------");
                })
                .then(Mono.just(atomicInteger.getAndIncrement()));
    }

}
