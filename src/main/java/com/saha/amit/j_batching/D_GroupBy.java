package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
When we have low cardinality say items are or type color so the variety is less
We can use group by which segregates items based on the type of item
 */
public class D_GroupBy {
    public static void main(String[] args) {


//        Flux.range(1, 5)
//                .delayElements(Duration.ofSeconds(1))
//                .groupBy(i -> i % 2)  // key 0, 1
//                .subscribe(gf -> process(gf, gf.key()));
//        Util.sleepSeconds(3);

        Flux.range(1, 30)
                .delayElements(Duration.ofMillis(500))
                .map(i -> Util.faker().superhero().name() + " | " + Util.faker().space().planet())
                //.doOnNext(i-> System.out.println(i))
                //.doOnNext(i -> System.out.println(i.split("\\|")[1]))
                .groupBy(i -> i.split("\\|")[1])              // Function to split and take the color
                .subscribe(gf -> process2(gf, gf.key()));
        Util.sleepSeconds(60);

    }

    private static void process2(Flux<String> flux, String key) {
        System.out.println("Called" + key);
        flux.subscribe(i -> System.out.println("Key : " + key + ", Item : " + i));
    }

    private static void process(Flux<Integer> flux, int key) {
        System.out.println("Called" + key);
        flux.subscribe(i -> System.out.println("Key : " + key + ", Item : " + i));
    }
}
