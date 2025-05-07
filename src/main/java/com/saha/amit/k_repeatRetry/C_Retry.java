package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class C_Retry {

    public static void main(String[] args) {
        //demo1();
        //demo2();
        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure())
                )
                .subscribe(Util.subscriber());
    }



    private static Mono<String> getCountryName() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 3) {
                        throw new RuntimeException("oops");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> System.err.println("ERROR: {}" + err.getMessage()))
                .doOnSubscribe(s -> System.out.println("subscribing"));
    }
}
