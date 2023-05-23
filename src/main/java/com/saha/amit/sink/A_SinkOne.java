package com.saha.amit.sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class A_SinkOne {
    public static void main(String[] args) {
        //Sink has 2 parts one emits one subscribes
        Sinks.One<Object> sink = Sinks.one(); // From this we will emit a mono type of object
        Mono<Object> mono = sink.asMono();  //From this we will subscribe

        mono.subscribe(Util.subscriber("BOBO"));
        //sink.tryEmitValue("CHELO");

        //Emit value internally implements tryEmitValue but has param for handling error
        sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println("signalType1 "+signalType.name());
            System.out.println("emitResult1 "+emitResult.name());
            return false;
        });

        sink.emitValue("hello", (signalType, emitResult) -> {
            //Below will execute in case of an error like here when we are trying to emit multiple values
            System.out.println("signalType2 "+signalType.name());
            System.out.println("emitResult2 "+emitResult.name());
            return false;  // Based on the value of signalType and emitResult we can decide to return true of false which determines if we want to retry or not
        });
    }
}
