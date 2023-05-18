package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class A_BatchWithBuffer {
    //Suppose if we want to capture user click events and insert in DB we don't want to do DB insertion per click
    // instead we want to do DB insertion in batches
    public static void main(String[] args) {

//        eventStreamNormalSteadyLoad()
//                .buffer(5)  // now it will return list of items instead of each item individually so that it can be processed in batches
//                .subscribe(Util.subscriber());
//        // If the load is less than it wil wait for buffer, so we can use duration
//        eventStreamNormalSteadyLoad()
//                .buffer(Duration.ofSeconds(2))  // now it will wait or a duration and all records in that period will be processed
//                .subscribe(Util.subscriber());

        //if the load is variable sometimes high sometimes low
        eventStreamHighLoad()
                .bufferTimeout(5, Duration.ofSeconds(2))  // now it will wait or a duration or buffer size which ever satisfies 
                .subscribe(Util.subscriber());
        Util.sleepSeconds(10);

    }

    private static Flux<String> eventStreamNormalSteadyLoad(){
        return Flux.interval(Duration.ofMillis(300))
                //.take(3)  //If items are less than the buffer size it will not block but return what it has
                .map(i->"event"+ i);
    }

    private static Flux<String> eventStreamHighLoad(){
        return Flux.interval(Duration.ofMillis(10))
                .map(i->"event"+ i);
    }

}
