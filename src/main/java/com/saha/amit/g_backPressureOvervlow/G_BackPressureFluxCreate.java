package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class G_BackPressureFluxCreate {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small","16");
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed "+ i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                }, FluxSink.OverflowStrategy.DROP)   // Can pass strategy during create
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
    }
}
