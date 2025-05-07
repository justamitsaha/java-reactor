package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class C_SinkThreadSafety {

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1(){
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        // arraylist is not thread safe.
        // intentionally chosen for demo purposes.
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }

        Util.sleepSeconds(2);

        System.out.println("list size: {}"+ list.size());
    }

    private static void demo2(){
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        // arraylist is not thread safe.
        // intentionally chosen for demo purposes.
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (signal, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }

        Util.sleepSeconds(2);

        System.out.println("list size: {}"+ list.size());
    }
}
