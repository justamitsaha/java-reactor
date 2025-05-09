package com.saha.amit.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static Consumer<Object> onNext() {
        return o -> log.info("Received : " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> log.info("ERROR : " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> log.info("Completed");
    }

    public static final Faker FAKER = Faker.instance();

    public static Faker faker(){
        return FAKER;
    }

    public static void sleepSeconds(int seconds){
        sleepMillis(seconds * 1000);
    }

    public static void sleepMillis(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getNamesList(int count){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            sleepSeconds(1);
            list.add(FAKER.funnyName().name());
        }
        return list;
    }

    public static Flux<String>  getNamesFlux(int count){
        return Flux
                .range(0,count)
                .map(n->{
                    sleepSeconds(1);
                    return FAKER.superhero().name();
                });
    }

    public static Subscriber<Object> subscriber(){
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> subscriber(String name){
        return new DefaultSubscriber(name);
    }

}
