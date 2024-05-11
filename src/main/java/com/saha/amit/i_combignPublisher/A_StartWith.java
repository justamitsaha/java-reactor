package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/*
Suppose we have a name generator which takes some time and is slow
What we can do is we can cache it and when it is called we can use start with this cache
so that subscribers can get initial value from cache
 */

public class A_StartWith {
    public static  List<String> list = new ArrayList<>();
    public static Flux<String> generateName(String s) {
        return Flux.generate(stringSynchronousSink -> {
                    System.out.println(s +" - Generating name");
                    Util.sleepSeconds(2);                                           //Here name generator takes time
                    String name = Util.faker().funnyName().name();
                    list.add(name);                                                     // Adds  to static cache
                    stringSynchronousSink.next(name);
                })
                .cast(String.class)
                .startWith(getNamesFromCache());                    // When it is taking time it can return item from cache
    }

    private static  Flux<String> getNamesFromCache() {
        return Flux.fromIterable(list);
    }
    public static void main(String[] args) {
        generateName("Amit")
                .take(2)
                .subscribe(Util.subscriber("Amit"));        // Amit is first one to get, so it takes time to return  as it is not from cache

        generateName("Jake")
                .take(2)
                .subscribe(Util.subscriber("Jake"));        // Jake gets from cache instantly

        generateName("Paul")
                .take(3)
                .subscribe(Util.subscriber("Paul"));        //Paul gets 2 items from cache 1 from actual method, so we see "Generating name" in console

    }

}
