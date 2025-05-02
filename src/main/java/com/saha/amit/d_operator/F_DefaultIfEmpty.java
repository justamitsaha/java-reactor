package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
If the source is not emitting any single data then we can take user to
1- defaultIfEmpty --> Returns default value
2- switchIfEmpty  --> Call a fallback method to return the response
But both will take effect only when source is not emitting any data
One good use case redis cache if empty
*/
public class F_DefaultIfEmpty {
    public static void main(String[] args) {
        //defaultIfEmptyMono();
        //defaultIfEmptyFlux();
        //defaultIfEmptyFluxNonEmpty();
        switchIfEmpty();
    }

    public static void defaultIfEmptyMono() {
        Mono.empty()
                .defaultIfEmpty("Fallback")
                .subscribe(System.out::println,
                        System.err::println,
                        () -> System.out.println("Completed"));
    }

    public static void defaultIfEmptyFlux() {
        Flux.range(1, 10)
                .filter(i -> i > 20)  // No items pass the filter
                .defaultIfEmpty(50)   // Fallback for empty Flux
                .subscribe(System.out::println,
                        System.err::println,
                        () -> System.out.println("Completed"));
    }

    public static void defaultIfEmptyFluxNonEmpty() {
        Flux.range(1, 10)
                .filter(i -> i > 9)  // Emits "10"
                .defaultIfEmpty(50)  // Ignored (stream not empty)
                .subscribe(System.out::println,
                        System.err::println,
                        () -> System.out.println("Completed"));
    }

    public static void switchIfEmpty(){
                Flux.range(1, 5)
                .filter(i->i>10)
                .switchIfEmpty(fallBack()) //If the main method provides even one response it will not go to fallback
                .subscribe(Util.subscriber());
    }


    public static Flux<Integer> fallBack() {
        return Flux.range(1, 12);
    }
}
