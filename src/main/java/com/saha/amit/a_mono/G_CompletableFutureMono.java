package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class G_CompletableFutureMono {
    private static final Logger log = LoggerFactory.getLogger(G_CompletableFutureMono.class);
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
        log.info("Inside getName");
        return CompletableFuture.supplyAsync(() -> {
            Util.sleepSeconds(1);
            log.info("Getting Name");
            return Util.faker().lordOfTheRings().character();
        });
    }
}
