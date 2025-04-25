package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class G_CompletableFutureMono {
    public static void main(String[] args) {

        getName();      //This will execute the getName()
        Mono.fromFuture(getName()); //This will execute the getName()  even without subscription
        Mono.fromFuture(() ->getName())  // This will not execute if we comment out the subscriber
                .subscribe(
                        Util.onNext()
                );

        Util.sleepSeconds(3);
    }

    //This is not a Lazy which means will be called when executed
    public static CompletableFuture<String> getName() {
        System.out.println("Inside getName");
        return CompletableFuture.supplyAsync(() -> {
            Util.sleepSeconds(1);
            System.out.println("Getting Name");
            return Util.faker().lordOfTheRings().character();
        });
    }
}
