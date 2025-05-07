package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class H_Replay {
    public static void main(String[] args) {

        //Sinks.Many<Object> sink = Sinks.many().replay().all();
        Sinks.Many<Object> sink = Sinks.many().replay().limit(1);
        Flux<Object> flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new msg");

    }
}
