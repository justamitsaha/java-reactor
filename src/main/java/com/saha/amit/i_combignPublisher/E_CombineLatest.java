package com.saha.amit.i_combignPublisher;

import com.github.javafaker.Faker;
import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class E_CombineLatest {
    public static void main(String[] args) {
        Flux.combineLatest(
                getNames(),
                getSalary(),
                (s,i)->s +" <--> "+i
        ).subscribe(Util.subscriber());
        Util.sleepSeconds(10);
    }
    public static Flux<String> getNames(){
        return Flux.just("A","B","C","D")
                .delayElements(Duration.ofSeconds(1));
    }
    public static Flux<Integer> getSalary(){
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(3));
    }
}
