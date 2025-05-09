package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class H_PublishOnSubscribeOn {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    fluxSink.next(1);
                })
                .doFirst(() -> printThreadName("doFirst-4"))
                .publishOn(Schedulers.newParallel("Amit"))
                .doFirst(() -> printThreadName("doFirst-3"))
                .doOnNext(i -> printThreadName("next " + i));

        flux.doFirst(() -> printThreadName("doFirst-2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("doFirst-1"))
                .subscribe(s -> printThreadName("sub" + s));
        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
