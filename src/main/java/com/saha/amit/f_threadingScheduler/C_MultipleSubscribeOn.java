package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*In case of multiple subscribeOn the one closer to Source takes precedence
Say I am the developer of the Publisher and I know better which thread model to use
I don't want others to overwrite my work hence the one I have written closer to source will take precedence
*/
public class C_MultipleSubscribeOn {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");                  //
                    fluxSink.next(1);
                })
                .subscribeOn(Schedulers.newParallel("Amit"))
                .doOnNext(i -> printThreadName("next " + i));   //newParallel

        flux
                .doFirst(() -> printThreadName("doFirst2"))     //boundedElastic
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("doFirst1"))     // Main thread
                .subscribe(s -> printThreadName("sub" + s));    // newParallel
        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
