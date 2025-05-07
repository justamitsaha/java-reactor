package com.saha.amit.m_cotext;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class C_ContextPropagation {

    public static void main(String[] args) {

        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(2);

    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(ctx -> {
                    System.out.println("producer1: {}"+ ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2() {
        return Mono.<String>deferContextual(ctx -> {
                    System.out.println("producer2: {}"+ ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }
}
