package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class B_SinkUniCast {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("BOBO"));
        //This will not work as uni cast will have one subscriber
        flux.subscribe(Util.subscriber("LELO"));
        sink.tryEmitNext("hello");
        sink.tryEmitNext("baba");
    }
}
