package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
Let's say we want to book ticket, and we have an aggregator service which gets flights from different operators Qatar, Emirates, American
Each operator can take its time, and it may or may not return flights
Here we can use merge operator, it will return flights from any operator which is returning value
No order will be maintained whichever operator is returning it will return to subscriber
 */
public class C_Merge {

    public static void main(String[] args) {
        Flux.merge(
                getAmericanFlights(),
                getQatarFlights(),
                getQatarFlights()
        ).subscribe(Util.subscriber());

        Util.sleepSeconds(6);

    }
    public static Flux<String> getQatarFlights(){
        return Flux.range(1,3)
                .delayElements(Duration.ofSeconds(1))                               // delay
                .filter(i -> Util.faker().random().nextBoolean())                   //may return may not return
                .map(i->"Qatar-"+i+"-"+ Util.faker().funnyName().name());
    }
    public static Flux<String> getEmiratesFlights(){
        return Flux.range(1,3)
                .delayElements(Duration.ofSeconds(1))
                .filter(i -> Util.faker().random().nextBoolean())
                .map(i->"Emirates-"+i+"-"+ Util.faker().funnyName().name());
    }
    public static Flux<String> getAmericanFlights(){
        return Flux.range(1,3)
                .delayElements(Duration.ofSeconds(1))
                .filter(i -> Util.faker().random().nextBoolean())
                .map(i->"American-"+i+"-"+ Util.faker().funnyName().name());
    }

}
