package com.saha.amit.flux;

import reactor.core.publisher.Flux;

public class A_FluxJust {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4);
        flux.subscribe(
                System.out::println,
                System.out::println,
                System.out::println
        );
    }
}
