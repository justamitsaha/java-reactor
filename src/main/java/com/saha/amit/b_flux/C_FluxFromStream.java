package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class C_FluxFromStream {
    public static void main(String[] args) {
        List<String> list = List.of("abs", "bbs", "ccs");
        Stream<String> stream = list.stream();

        //Flux from Stream
        Flux<String> flux = Flux.fromStream(stream);
        //Flux<String> flux = Flux.fromStream(() -> list.stream());
        flux.subscribe(
                s -> System.out.println(s),
                e -> System.out.println(e),
                () -> System.out.println("completed stream 1")
        );

        flux.subscribe(s -> System.out.println(s));             // Since this flux is from stream we can't subscribe again as stream is closed

        List<Integer> numbers = List.of(1, 2, 3, 4);

        // Use Supplier to create new stream for each subscriber
        Flux<Integer> flux2 = Flux.fromStream(numbers::stream); // Method reference

        // Now both work
        flux2.subscribe(num -> System.out.println("Sub1: " + num));
        flux2.subscribe(num -> System.out.println("Sub2: " + num));
    }
}
