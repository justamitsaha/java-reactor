package com.saha.amit.operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.concurrent.Queues;

import java.time.Duration;

public class D_Delay {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.x", "9");
        Flux.range(1, 10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
//                .onErrorReturn(-1)                // returns error
//                .onErrorResume(e -> fallback())   // implements fallback
                .onErrorContinue((err,obj)->{       // Continues execution
                    System.out.println("Error"+ err +" for "+ obj);
                })
                .subscribe(Util.subscriber());

    }
    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
