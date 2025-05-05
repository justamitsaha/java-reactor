package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
If we do subscribeOn it doesn't mean  all items will be published using different thread
it means it will create a new thread for the subscriber in thread pool
 */
public class D_MultipleSubscriber {
    public static void main(String[] args) {


        Flux<Object> flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(1);                   // New thread is created but all items emitted using same new thread
            }
        }).doOnNext(i -> printThreadName("next " + i));

        flux.subscribeOn(Schedulers.boundedElastic())
                .subscribe(s -> printThreadName("sub" + s));
        Util.sleepSeconds(5);

        System.out.println("multiple subscribe");
        for (int i = 0; i < 5; i++) {
            flux.subscribeOn(Schedulers.boundedElastic())
                    .subscribe(s -> printThreadName("sub" + s));        // When we have multiple subscriber each subscriber will have its own thread
        }

        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
