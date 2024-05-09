package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class F_Timeout {
    public static void main(String[] args) {

        getOrderNumber()
                .timeout(Duration.ofSeconds(1),fallback())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(7);

    }
    
    /*This method takes 2 seconds to emit but there is a timeout of 1 seconds
    So it will go to fallback method*/
    public static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(2));
    }

    public static Flux<Integer> fallback() {
        return Flux.range(1, 6);
    }
}
