package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class D_LimitRateMultipleSubscriber {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            System.out.println("generating -> "+ state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .subscribeOn(Schedulers.parallel())
                .cast(Integer.class);

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())         //slow consumer
                .map(D_LimitRateMultipleSubscriber::timeConsumingTask)
                .subscribe(Util.subscriber());

        producer                                                //Fast consumer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
