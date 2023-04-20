package com.saha.amit.flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class D_FlexRange {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(5,10);
        flux
                .map(n-> Util.faker().superhero().name())
                .subscribe(
                        System.out::println
                );
    }
}
