package com.saha.amit.m_cotext;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class B_ContextUpdateAppendDelete {

    public static void main(String[] args) {
        update();
    }

    private static void append(){
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void update(){
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(ctx -> ctx.delete("c"))
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            System.out.println("{}"+ ctx);
            if(ctx.hasKey("user")){
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
