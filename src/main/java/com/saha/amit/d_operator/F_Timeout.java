package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class F_Timeout {
    public static void main(String[] args) {
        multipleTimeout();
    }

    public static void timeout() {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(2))       // delay
                .timeout(Duration.ofSeconds(1))  //Timeout
                .subscribe(Util.subscriber());
        Util.sleepSeconds(7);
    }

    public static void timeout2() {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(2))       // delay
                .timeout(Duration.ofSeconds(1), fallback())  //Timeout with fallback
                .subscribe(Util.subscriber());
        Util.sleepSeconds(7);
    }

    public static Flux<Integer> fallback() {
        return Flux.range(1, 6);
    }

    public static void multipleTimeout() {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(2))       // delay
                .timeout(Duration.ofSeconds(1), fallback())  //Timeout with fallback
                .timeout(Duration.ofMillis(200))        // This will take precedence
                .subscribe(Util.subscriber());
        Util.sleepSeconds(7);
    }
}
