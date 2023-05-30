package com.saha.amit.m_cotext;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class A_Context {
    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "sam"))
                .contextWrite(Context.of("user", "dam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
           if (contextView.hasKey("user")){
               return Mono.just("Welcome "+ contextView.get("user"));
           } else {
               return Mono.error(new RuntimeException("Un-authenticated"));
           }
        });
    }
}
