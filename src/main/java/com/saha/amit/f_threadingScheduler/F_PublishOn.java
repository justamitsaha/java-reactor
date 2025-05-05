package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
Subscribe on was for publisher which goes upstream
Published on is for subscriber which goes downstream
 */
public class F_PublishOn {
    public static void main(String[] args) {

        Flux.range(1, 2)
                .doOnNext(i -> System.out.println("Generating " + i + " on " + Thread.currentThread().getName()))
                .publishOn(Schedulers.parallel()) // First switch: parallel
                .doOnNext(i -> System.out.println("Value " + i + " on " + Thread.currentThread().getName()))
                .publishOn(Schedulers.boundedElastic()) // Second switch: boundedElastic
                .subscribe(i -> System.out.println("Subscriber received " + i + " on " + Thread.currentThread().getName()));
        Util.sleepSeconds(3);
    }

}
