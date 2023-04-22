package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import java.util.Arrays;
import java.util.List;

public class B_FluxFromListArray {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().howIMetYourMother().catchPhrase());
        Flux<String> stringFlex = Flux.fromIterable(list);
        stringFlex.subscribe(
                System.out::println
        );

        String[] arr = {
                Util.faker().superhero().name(),
                Util.faker().superhero().name(),
                Util.faker().superhero().name()
        };
        Flux<String> flux = Flux.fromArray(arr);
        flux.subscribe(System.out::println);
    }
}
