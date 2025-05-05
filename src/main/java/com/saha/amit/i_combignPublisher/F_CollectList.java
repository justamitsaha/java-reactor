package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class F_CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .collectList()                // Transforms Flux<Integer> to Mono<List<Integer>>
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .concatWith(Mono.error(new RuntimeException()))
                .collectList()
                .subscribe(Util.subscriber());


    }
}
