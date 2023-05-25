package com.saha.amit.sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class D_SinkMultiCast {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("BOBO"));
        flux.subscribe(Util.subscriber("LELO"));
        sink.tryEmitNext("hello");
        sink.tryEmitNext("baba");
    }
}
