package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/*
Here we are emitting 500 items but subscriber is getting lesser items these are dropped based on the buffer size
defined in reactor.bufferSize.small","16 in reactor Queue class
 */
public class B_OverFlowDrop {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();

        System.setProperty("reactor.bufferSize.small","16");
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        //System.out.println("Pushed : "+ i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })

                //.onBackpressureDrop()
                .onBackpressureDrop(s-> lst.add((Integer) s))  // We can catch the dropped values like this
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());
        Util.sleepSeconds(25);
        System.out.println(lst);
    }
}
