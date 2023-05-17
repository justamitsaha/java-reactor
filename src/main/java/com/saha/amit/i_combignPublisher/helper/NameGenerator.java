package com.saha.amit.i_combignPublisher.helper;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private List<String> list = new ArrayList<>();

    public Flux<String> generateName(String s) {
        return Flux.generate(stringSynchronousSink -> {
                    System.out.println(s +" - Generating name");
                    Util.sleepSeconds(1);
                    String name = Util.faker().funnyName().name();
                    list.add(name);
                    stringSynchronousSink.next(name);
                })
                .cast(String.class)
                .startWith(getNamesFromCache());
    }

    private Flux<String> getNamesFromCache() {
        return Flux.fromIterable(list);
    }

}
