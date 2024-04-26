package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class D_MonoWithSupplierCallable {
    public static void main(String[] args) {

        /*Just should be called when we have the data ready and no waiting logic. In below case
        * if we call getName inside Mono.just(), then even if no one subscribes to it, getName()
        * will be called. In reactive programming programs should execute only when subscribed*/
        Mono<String> mono = Mono.just(getName());

        /*When result is not ready we should no call just() instead should call fromSupplier which
        takes supplier interface as parameter*/
        Mono<String> mono1 = Mono.fromSupplier(() -> getName());
        mono1.subscribe(
                Util.onNext()
        );

        //Another way for declaring supplier same can be done with callable
        Supplier<String> supplier = D_MonoWithSupplierCallable::getName;
        Mono<String> mono4 = Mono.fromSupplier(supplier);

        Callable<String> callable = D_MonoWithSupplierCallable::getName;
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
