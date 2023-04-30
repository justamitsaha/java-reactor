package com.saha.amit.operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class F_IfEmpty {
    public static void main(String[] args) {
        getOrderNumber()
                .filter(i->i>10)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());

        getOrderNumber()
                .filter(i->i>10)
                .switchIfEmpty(fallBack()) //If the main method provides even one response it will not go to fallback
                .subscribe(Util.subscriber());

    }

    public static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 5);
    }

    public static Flux<Integer> fallBack() {
        return Flux.range(1, 12);
    }
}
