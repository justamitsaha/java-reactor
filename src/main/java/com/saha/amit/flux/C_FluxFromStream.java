package com.saha.amit.flux;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class C_FluxFromStream {
    public static void main(String[] args) {
        List<String> list = List.of("a","b","s");
        Stream<String> stream = list.stream();

        //Flux<String> flux1 = Flux.fromStream(stream);
        Flux<String> flux = Flux.fromStream(()->list.stream());
        flux.subscribe(
                s -> System.out.println(s),
                e -> System.out.println(e),
                ()-> System.out.println("completed")
        );
        flux.subscribe(s -> System.out.println(s));
    }
}
