package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class D_FluxEagerBehaviour {

    private static final Logger log = LoggerFactory.getLogger(D_FluxEagerBehaviour.class);

    public static void main(String[] args) {
        produceEarly();
    }

    private static void produceEarly() {
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(
                s -> log.info("Received " + s),
                e -> log.error(String.valueOf(e)),
                () -> log.info("Completed"),
                subscription -> subscription.request(2)  // All items are generated even if only 2 are requested
        );
    }

    private static void produceOnDemand() {
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxSink.next(name);
                }
            });
            fluxSink.complete();
        }).subscribe(
                s -> log.info("Received " + s),
                e -> log.error(String.valueOf(e)),
                () -> log.info("Completed"),
                subscription -> subscription.request(2)  // All items are generated even if only 2 are requested
        );
    }

}
