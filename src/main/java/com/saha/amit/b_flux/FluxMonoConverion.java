package com.saha.amit.b_flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoConverion {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello");
        Flux<String> flux = Flux.from(mono);
        flux.subscribe(s -> System.out.println(s));

        Flux.range(1,10)
                .filter(s->s==3)
                .next()
                .subscribe(
                        s-> System.out.println(s),
                        e -> System.out.println(e),
                        ()-> System.out.println()
                );

    }
}
