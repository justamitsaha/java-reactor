package com.saha.amit.i_combignPublisher;

import com.github.javafaker.Faker;
import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
In Zip, we wait for the elements from all publishers, but it gives 1st element of all publisher when all publisher is ready
For e.g. even if we have Tyre no 100 is ready it will emit the first element
Suppose we want stock information in that case we want the latest item here we use combing latest
It takes 2 publisher and a bi-function to manipulate the result
It will wait for both publisher to be ready and once any one of them is ready it will emit latest of both
Reliance ready wait for nestle
Both ready emit both
Reliance another one emitted nestle previous one present emit again like this
 */
public class E_CombineLatest {
    public static void main(String[] args) {
        Flux.combineLatest(
                getNestleStock(),
                getRelianceStock(),
                (s,i)->s +" <--> "+i
        ).subscribe(Util.subscriber());
        Util.sleepSeconds(10);
    }
    public static Flux<String> getRelianceStock(){
        return Flux.range(1,6)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> "Reliance- " +Util.faker().howIMetYourMother().catchPhrase());
    }

    public static Flux<String> getNestleStock(){
        return Flux.range(1,2)
                .delayElements(Duration.ofSeconds(3))
                .map(integer -> "Nestle-" +Util.faker().funnyName().name());
    }
}
