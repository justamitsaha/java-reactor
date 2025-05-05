package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/* Here we see that by default both publisher and subscriber is running in same thread
 */
public class A_DefaultThreadPubSub {
    public static void main(String[] args) {
        //demo1();
        demo2();
    }

    public static void demo1() {
        //Everything executed in main thread
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    fluxSink.next(1);
                })
                .doOnNext(i -> printThreadName("next " + i));
        flux.subscribe(s -> printThreadName("sub" + s));
    }

    public static void demo2() {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    fluxSink.next(1);
                })
                .doOnNext(i -> printThreadName("next " + i));

        // Forcing it to run on different thread both publisher i.e. Flux create above and subscriber below
        // are not on main thread
        Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));
        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
