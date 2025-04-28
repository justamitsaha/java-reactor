package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B_FluxFromListArray {

    private static final Logger log = LoggerFactory.getLogger(B_FluxFromListArray.class);
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().howIMetYourMother().catchPhrase());

        //Flux from List
        Flux<String> stringFlex = Flux.fromIterable(list);
        stringFlex.subscribe(
                System.out::println,
                System.out::println,
                () -> log.info("Flux from List completed")
        );

        /*Since Map is not part of Collections and doesn't implement iterable can't create
        Flux directly from Map*/
        Map<String, Integer> map = Map.of("A", 1, "B", 2);

        Flux<Map.Entry<String, Integer>> entryFlux = Flux.fromIterable(map.entrySet());

        entryFlux.subscribe(entry ->
                log.info(entry.getKey() + ": " + entry.getValue())
        );


        //Flux from Array
        String[] arr = {
                Util.faker().superhero().name(),
                Util.faker().superhero().name(),
                Util.faker().superhero().name()
        };
        Flux<String> flux = Flux.fromArray(arr);
        flux
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Util.sleepSeconds(10);               //Needed when we are delaying above
    }
}
