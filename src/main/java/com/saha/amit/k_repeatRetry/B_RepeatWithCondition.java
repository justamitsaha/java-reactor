package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class B_RepeatWithCondition {

    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        getInteger()
                .repeat(() -> atomicInteger.get() <2 )
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getInteger(){
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("Subscribed 1"))
                .doOnComplete(() -> System.out.println("Completed 2"))
                .map(i-> atomicInteger.getAndIncrement());

    }
}
