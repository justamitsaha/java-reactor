package com.saha.amit.l_sink;

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
        for (int i = 0; i < 1000; i++) {
            final int count = i;
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(count);
            });
        }
        Util.sleepSeconds(2);
        System.out.println("List size without handling concurrency --> "+list.size());


        list.clear();

        for (int i = 0; i < 1000; i++) {
            final int count = i;                                                                                // Variable used in lambda expression should be final or effectively final
            CompletableFuture.runAsync(()->{
                sink.emitNext(count, (signalType, emitResult) -> true);         //return true tells that if you face any error retry
            });
        }
        Util.sleepSeconds(2);
        System.out.println("List size without handling concurrency --> "+list.size());  // We have to fetch size after sleep as we are retrying, so it may take some time
    }
}
