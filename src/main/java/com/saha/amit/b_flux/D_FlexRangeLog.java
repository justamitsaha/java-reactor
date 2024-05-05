package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class D_FlexRangeLog {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(5, 10);
        flux
                .log()
                .map(n -> Util.faker().superhero().name())
                .log()
                .subscribe(
                        System.out::println
                );
    }
}
