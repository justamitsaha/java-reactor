package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*Instead of manually create thread we can rely on Reactor framework to create thread for us
This can be done using subscribeOn which is for publisher (upstream) which takes a Scheduler instance
For this we can use factory methods
1>Schedulers.boundedElastic() --> For Network/Time consuming tasks, can spawn multiple thread,
    Say the CPU has 4 cores can create 40 threads
2>Schedulers.parallel() --> CPU intensive tasks, one thread per CPU
3>Schedulers.single() --> Single dedicated thread for one-off tasks
4>Schedulers.immediate() --> current thread
If you are not sure which one to use boundedElastic()
*/
public class B_SubscribeOn {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");                  //In new thread
                    fluxSink.next(1);
                })
                .doOnNext(i -> printThreadName("next " + i));   //In new thread

        flux
                .doFirst(() -> printThreadName("doFirst2"))     //In new thread
                .subscribeOn(Schedulers.boundedElastic())       //After this in new thread
                .doFirst(() -> printThreadName("doFirst1"))     //This will be in main thread
                .subscribe(s -> printThreadName("sub " + s));

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
