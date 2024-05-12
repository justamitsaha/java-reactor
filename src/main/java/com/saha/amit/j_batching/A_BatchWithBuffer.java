package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*Suppose if we want to capture user click events and insert in DB we don't want to do DB insertion per click
instead we want to do DB insertion in batches*/
public class A_BatchWithBuffer {

    public static void main(String[] args) {

        System.out.println("Streaming in batch of 5");
        eventStreamNormalSteadyLoad()
                .buffer(5)  // List of 5 events returned
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);

        System.out.println("Streaming based on time");
        eventStreamNormalSteadyLoad()
                .buffer(Duration.ofSeconds(1))  // process all items produced in 1 seconds
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);

        System.out.println("Streaming based on time and batch");
        eventStreamHighLoad()
                    .bufferTimeout(5, Duration.ofSeconds(2))  //both time and batch mentioned which ever satisfies first
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<String> eventStreamNormalSteadyLoad() {
        return Flux.interval(Duration.ofMillis(100))
                .take(25)
                //.take(3)  //If items are less than the buffer size it will not block but return what it has
                .map(i -> "event" + i);
    }

    private static Flux<String> eventStreamHighLoad() {
        return Flux.interval(Duration.ofMillis(Util.faker().random().nextInt(10,50)))
                .take(25)
                .map(i -> "event" + i);
    }

}
