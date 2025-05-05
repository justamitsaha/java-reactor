package com.saha.amit.g_backPressureOvervlow;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
Here we are emitting 500 items but subscriber is getting lesser items these are dropped based on the buffer size
defined in reactor.bufferSize.small","16 in reactor Queue class
 */
public class B_AutomaticBackPressureHandling {
    public static void main(String[] args) {

        //System.setProperty("reactor.bufferSize.small", "16");

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
                //.publishOn(Schedulers.boundedElastic())
                .map(B_AutomaticBackPressureHandling::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
