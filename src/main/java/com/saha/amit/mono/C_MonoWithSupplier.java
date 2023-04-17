package com.saha.amit.mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class C_MonoWithSupplier {
    public static void main(String[] args) {

        //The method is getting called even without subscribing which is wrong
        //Use just when data is ready
        Mono<String> mono = Mono.just(getName());

        Supplier<String> supplier = C_MonoWithSupplier::getName;

        Mono<String> mono1 = Mono.fromSupplier(supplier);
        mono1.subscribe(
                Util.onNext()
        );

        Callable<String> callable = C_MonoWithSupplier::getName;
        Mono<String> mono2= Mono.fromCallable(callable);
        mono2.subscribe(
                Util.onNext()
        );

    }

    public static String getName(){
        Util.sleepSeconds(2);
        String name = Util.faker().lordOfTheRings().character();
        System.out.println("Generating name "+ name);
        return name;
    }
}
