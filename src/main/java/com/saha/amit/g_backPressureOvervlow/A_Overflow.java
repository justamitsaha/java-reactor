package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
Here producer  produced all the 500 items even before subscriber processed one item due to delay in subscriber
So how it works is by default is that , the reactor, tries to keep things in the memory when subscriber  cannot consume
this is not good because the objects  can be big causing out of memory exception
This is the default strategy lets look at all strategy
1> Buffer --> default keeps items in memory
2> Drop --> Once queue is full drop items
3> Latest --> Once queue is full drop all keep one
3> Error --> Throw Error

 */
public class A_Overflow {
    public static void main(String[] args) {
        //Publisher is very fast
        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            for (int i = 0; i < 501; i++) {
                fluxSink.next(i);
                System.out.println("Pushed " + i);
            }
            fluxSink.complete();
        });

        //Subscriber has delay of 10 ms for each item
        objectFlux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
