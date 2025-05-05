package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*Suppose if we want to capture user click events and insert in DB we don't want to do DB insertion per click
instead we want to do DB insertion in batches*/
public class A_BatchWithBuffer {

    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
        demo4();
        Util.sleepSeconds(60);

    }

    private static void demo1() {
        eventStream()
                .buffer() // int-max value or the source has to complete
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream()
                .buffer(3) // every 3 items
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(500)) // every 500ms
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1)) // every 3 items or max 1 second
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                //.take(10)
                .concatWith(Flux.never())   //Makes it never ending stream
                .map(i -> "event-" + (i + 1));
    }

}
