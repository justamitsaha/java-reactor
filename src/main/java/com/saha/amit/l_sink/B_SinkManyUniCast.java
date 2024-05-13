package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


/*
Sinks.many().uniCast()  is used to create Flux which can have many subscribers
 */
public class B_SinkManyUniCast {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("BOBO"));
        flux.subscribe(Util.subscriber("LEO"));             //This will not work as uni cast will have one subscriber

        sink.tryEmitNext("hello");
        sink.tryEmitNext("baba");
    }
}
