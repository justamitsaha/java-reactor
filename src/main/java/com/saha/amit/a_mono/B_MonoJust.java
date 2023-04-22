package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class B_MonoJust {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        //If we don't subscribe to mono it will not execute
        System.out.println(mono);
        //Different overloaded versions of subscribe
        //Only success
        mono.subscribe(System.out::println);
        //Success, failure and error
        mono.subscribe(
                System.out::println,
                System.out::println,
                () -> System.out.println("completed")
        );

        Mono<Integer> mono2 = Mono.just("ball")
                .map(s -> s.length()/0);
        //If we don't handel error and error happens the exception will be thrown
        mono2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
