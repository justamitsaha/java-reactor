package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class E_Timeout {
    public static void main(String[] args) {

        getOrderNumber()
                .timeout(Duration.ofSeconds(1),fallback())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(7);

    }

    public static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(2));
    }

    public static Flux<Integer> fallback() {
        return Flux.range(1, 6);
    }
}
