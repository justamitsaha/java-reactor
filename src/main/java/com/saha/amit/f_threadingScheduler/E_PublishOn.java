package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
/*
Subscribe on was for publisher which goes upstream
Published on is for subscriber which goes downstream
 */
public class E_PublishOn {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(1);
                    }
                })
                .doOnNext(s -> printThreadName("publisher  --> " + s));

        flux
                .publishOn(Schedulers.boundedElastic())
                .subscribe(s -> printThreadName("subscriber --> " + s));
        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
