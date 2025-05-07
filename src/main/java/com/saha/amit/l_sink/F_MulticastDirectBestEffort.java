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
        demo2();
        Util.sleepSeconds(10);
    }

    /*
        When we have multiple subscribers, if one subscriber is slow,
        we might not be able to safely deliver messages to all the subscribers /
        other fast subscribers might not get the messages.
     */
    private static void demo1() {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            System.out.println("item: " + i + "result: " + result);
        }

    }

    /*
        directBestEffort - focus on the fast subscriber and ignore the slow subscriber
     */
    private static void demo2() {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().directBestEffort();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        //flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));
        //Solution for mike add back pressure
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            System.out.println("item: " + i + "result: " + result);
        }
    }
}