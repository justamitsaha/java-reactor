package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class D_MultipleSubscriber {
    public static void main(String[] args) {

        //In this case we have one subscriber for a publisher which emits multiple value here the one subscriber will emit in single thread
        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(1);
                    }
                })
                .doOnNext(i -> printThreadName("next " + i));

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(s -> printThreadName("sub" + s));
        Util.sleepSeconds(5);

        System.out.println("multiple subscribe");

        for (int i = 0; i < 5; i++) {
            flux
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe(s -> printThreadName("sub" + s));
        }
        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
