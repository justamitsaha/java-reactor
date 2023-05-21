package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class D_RetryWithDelay {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getInteger()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<Integer> getInteger() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed 1"))
                .doOnComplete(() -> System.out.println("Completed 2"))
                .map(i -> i / Util.faker().random().nextInt(0, 5) > 3 ? 0 : 1)
                .doOnError(err -> System.out.println(err));
    }
}
