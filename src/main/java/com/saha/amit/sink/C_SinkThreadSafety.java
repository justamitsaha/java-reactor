package com.saha.amit.sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class C_SinkThreadSafety {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        List<Object> list = new ArrayList<>();
        flux.subscribe(list::add);

        //Below is not thread safe in case of concurrency issues nothing is handled
        /*for (int i = 0; i < 1000; i++) {
            final int count = i;
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(count);
            });
        }*/

        //In below scenario in case of error it retries hence the result is correct
        for (int i = 0; i < 1000; i++) {
            final int count = i;
            CompletableFuture.runAsync(()->{
                sink.emitNext(count, (signalType, emitResult) -> true);
            });
        }
        Util.sleepSeconds(5);
        System.out.println(list.size());
    }
}
