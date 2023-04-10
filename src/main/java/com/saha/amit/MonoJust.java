package com.saha.amit;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        System.out.println(mono);
        mono.subscribe(System.out::println);
        mono.subscribe(
                System.out::println,
                System.out::println,
                () -> System.out.println("completed")
        );

        Mono<Integer> mono2 = Mono.just("ball")
                .map(s -> s.length()/0);
        mono2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
