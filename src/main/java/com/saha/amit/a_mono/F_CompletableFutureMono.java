package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class F_CompletableFutureMono {
    public static void main(String[] args) {
        Mono.fromFuture(getName()).subscribe(
                Util.onNext()
        );
        getName();
        Util.sleepSeconds(3);
    }

    public static CompletableFuture<String> getName(){
        System.out.println("Inside getName");
        return CompletableFuture.supplyAsync(()-> {
            Util.sleepSeconds(1);
            System.out.println("Getting Name");
            return Util.faker().lordOfTheRings().character();
        });
    }
}
