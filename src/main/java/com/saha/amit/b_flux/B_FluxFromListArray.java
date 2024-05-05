package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B_FluxFromListArray {
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
                () -> System.out.println("Flux from List completed")
        );

        /*Since Map is not part of Collections and doesn't implement iterable can't create
        Flux directly from Map*/


        String[] arr = {
                Util.faker().superhero().name(),
                Util.faker().superhero().name(),
                Util.faker().superhero().name()
        };
        //Flux from Array
        Flux<String> flux = Flux.fromArray(arr);
        flux
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Util.sleepSeconds(10);               //Needed when we are delaying above
    }
}
