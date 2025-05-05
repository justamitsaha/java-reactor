package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class E_VirtualThread {

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        System.out.println("generating: {}" + i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> System.out.println("value: {}"+ v))
                .doFirst(() -> System.out.println("first1-{}"+ Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> System.out.println("first2"));


        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);

    }
}
