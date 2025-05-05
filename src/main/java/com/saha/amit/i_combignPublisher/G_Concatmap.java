package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class G_Concatmap {
    public static void main(String[] args) {
        getGOTCharacterList()
                .doOnNext(character -> System.out.println("Character name " + character))
                .concatMap(E_Flatmap::getGOTQuotes)
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
