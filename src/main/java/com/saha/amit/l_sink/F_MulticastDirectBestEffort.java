package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class F_MulticastDirectBestEffort {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        //Since sam is slow due to delay both will miss values with directAllOrNothing
        //Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        //With directBestEffort sam will not miss values due to mike
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }
        Util.sleepSeconds(10);

    }
}