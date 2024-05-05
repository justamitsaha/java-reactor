package com.saha.amit.b_flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class H_FluxMonoConversion {
    public static void main(String[] args) {
        fluxFromMono();
        monoFromFlux();
    }

    /*
     * Say if we have a method which accepts flux, but we have mono.
     * So to satisfy the signature, we need flux to Mono conversion*/
    public static void fluxFromMono() {
        Mono<String> mono = Mono.just("Hello");
        Flux<String> flux = Flux.from(mono);
        flux.subscribe(s -> System.out.println(s));
    }

    public static void monoFromFlux(){
        Flux.range(1, 10)
                .filter(s -> s == 3)                        // Without this line, the method will return 1 as Mono and complete
                .next()                                         //This converts Flux to mono. When ever it receives the first item, it closes the flux and returns a Mono
                .subscribe(
                        s -> System.out.println(s),
                        e -> System.out.println(e),
                        () -> System.out.println()
                );

    }
}

