package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class B_OverFlowDrop {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small","16");
        //When 75 % of queue is full the next item will go in Queue . 75 % of 16 is 12, so the item pushed after 12th received item
        //will go in queue and will be received after 16th item is received
        List<Integer> lst = new ArrayList<>();
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed "+ i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                //.onBackpressureBuffer()  default
                .onBackpressureDrop(s-> lst.add((Integer) s))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
        System.out.println(lst);
    }
}
