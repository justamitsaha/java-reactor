package com.saha.amit.i_combignPublisher;


import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

public class E_Flatmap {

    public static void main(String[] args) {
        //demoFlatMap();
        //demoFlatMapMany();
        demoFlatMapFlux();
    }

    public static void demoFlatMap() {
        Mono<Mono<String>> map = getGOTCharacter(1).map(E_Flatmap::getGOTDragon);
        getGOTCharacter(1)
                .doOnNext(character -> System.out.println("Character name " + character))
                .flatMap(E_Flatmap::getGOTDragon)
                .subscribe(dragon -> System.out.println("Dragon name " + dragon));
        Util.sleepSeconds(2);
    }

    public static void demoFlatMapMany() {
        Mono<Flux<String>> map = getGOTCharacter(1).map(E_Flatmap::getGOTQuotes);

        getGOTCharacter(1)
                .doOnNext(character -> System.out.println("Character name " + character))
                .flatMapMany(E_Flatmap::getGOTQuotes)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);
        Util.sleepSeconds(6);
    }

    public static void demoFlatMapFlux() {
        Flux<String> flux = getGOTCharacterList().flatMap(E_Flatmap::getGOTQuotes);

        getGOTCharacterList()
                .doOnNext(character -> System.out.println("Character name " + character))
                //.flatMap(E_Flatmap::getGOTQuotes)
                .flatMap(E_Flatmap::getGOTQuotes,1)
                .subscribe(System.out::println);
        Util.sleepSeconds(10);
    }

    public static Flux<String> getGOTCharacterList() {
        return Flux.create(stringFluxSink -> {
            for (int i = 0; i < 5; i++) {
                stringFluxSink.next(Util.faker().gameOfThrones().character());
            }
            stringFluxSink.complete();
        });
    }

    public static Mono<String> getGOTCharacter(int id) {
        return Mono.fromSupplier(() -> Util.faker().gameOfThrones().character());
    }

    public static Mono<String> getGOTDragon(String character) {
        return Mono.fromSupplier(() -> Util.faker().gameOfThrones().dragon());
    }


    public static Flux<String> getGOTQuotes(String character) {
        return Flux.create(stringFluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        stringFluxSink.next(character + " -> " + Util.faker().gameOfThrones().quote());
                    }
                    stringFluxSink.complete();
                })
                .delayElements(Duration.ofMillis(new Random().nextInt(200, 500)))
                .cast(String.class);
    }
}


