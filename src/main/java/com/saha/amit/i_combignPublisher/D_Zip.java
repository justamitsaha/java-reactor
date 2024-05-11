package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
Suppose we have a car manufacturer where it produces 2 engine, 10 body and 100 tires
Even if other items are produced in large number car production is limited by engine
This is done by zip once one element of all types are ready it emits
 */
public class D_Zip {
    public static void main(String[] args) {
        Flux.zip(
                getBody(),
                getTyres(),
                getEngine()
        ).subscribe(Util.subscriber());

        Util.sleepSeconds(8);

    }

    public static Flux<String> getBody(){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> "Body " +integer);
    }

    public static Flux<String> getEngine(){
        return Flux.range(1,2)
                .delayElements(Duration.ofSeconds(3))
                .map(integer -> "Engine " +integer);
    }

    public static Flux<String> getTyres(){
        return Flux.range(1,100)
                .map(integer -> "Tyres " +integer);
    }
}
