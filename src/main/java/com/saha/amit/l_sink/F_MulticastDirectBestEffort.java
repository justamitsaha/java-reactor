package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
/*
Say we have 2 subscriber one slow one fast with directAllOrNothing  due to slow subscriber fast one will also miss
We can avoid that using directBestEffort
 */
public class F_MulticastDirectBestEffort {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        //With directBestEffort sam will not miss values due to mike
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        Flux<Object> flux = sink.asFlux();

        flux
                .subscribe(Util.subscriber("sam"));                         // sam is fast in processing
        flux
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("mike"));                        // mike is slow in processing

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }
        Util.sleepSeconds(10);

    }
}