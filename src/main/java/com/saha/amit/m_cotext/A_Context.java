package com.saha.amit.m_cotext;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class A_Context {
    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")){
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
