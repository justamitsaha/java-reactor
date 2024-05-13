package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
In multiCast we are storing message for 1st subscriber but after that subscriber will not get already send
or we can make sure no one will get using directAllOrNothing
 */
public class E_MulticastBuffer {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        flux.subscribe(Util.subscriber("Alex "));
        //Alex will get hello but Jones will miss it, because when there is no subscribers we are storing the items
        flux.subscribe(Util.subscriber("Jones "));
        sink.tryEmitNext("people");

        Sinks.Many<Object> sink1 = Sinks.many().multicast().directAllOrNothing();
        Flux<Object> flux1 = sink1.asFlux();
        sink1.tryEmitNext("Hello");
        flux1.subscribe(Util.subscriber("Alex 1 "));
        //With directAllOrNothing none will get the already emitted message are
        flux1.subscribe(Util.subscriber("Jones 1 "));
        sink1.tryEmitNext("people");
        Util.sleepSeconds(5);
    }
}
