package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
/*
When we put delay it delays the item. Then reactor doesn't emit all item at once 
it maintains an internal buffer of 32 and send 32 items at once.
This 32 is defined in Queue class in reactor.util.concurrent in property called reactor.bufferSize.x
*/
public class D_Delay {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.x", "9");
        Flux.range(1, 10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

    }
}
