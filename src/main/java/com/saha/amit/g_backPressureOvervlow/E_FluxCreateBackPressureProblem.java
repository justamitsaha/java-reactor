package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class E_FluxCreateBackPressureProblem {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        //When 75 % of queue is full the next item will go in Queue . 75 % of 16 is 12, so the item pushed after 12th received item
        //will go in queue and will be received after 16th item is received
        var producer = Flux.create(fluxSink -> {
                    for (int i = 1; i < 501 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        //System.out.println("Pushed " + i);
                        Util.sleepMillis(50);
                    }
                    fluxSink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.boundedElastic());

        producer
                //.onBackpressureLatest()
                //.onBackpressureError()
                //.onBackpressureBuffer(10)
                //.onBackpressureDrop()
                .onBackpressureLatest()
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(E_FluxCreateBackPressureProblem::timeConsumingTask)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
