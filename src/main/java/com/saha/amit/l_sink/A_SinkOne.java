package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*Sink has 2 parts one emits one subscribes*/
public class A_SinkOne {
    public static void main(String[] args) {

        Sinks.One<Object> sink = Sinks.one(); // From this we will emit a value
        Mono<Object> mono = sink.asMono();  //From this we will subscribe

        mono.subscribe(Util.subscriber("BOBO"));
        sink.tryEmitValue("CHELO");
        //sink.tryEmitValue("CHU<-->CHU");        // When we try to emit another value nothing happens as the sink is closed
        //sink.tryEmitEmpty();                                      // For empty
        //sink.tryEmitError(new RuntimeException());    //For error
        Util.sleepSeconds(2);

        //Emit value internally implements tryEmitValue but has param for handling error
        sink.emitValue("hi", (signalType, emitResult) -> {
            //This will execute only in case of error if we comment all previous emit it won't execute
            System.out.println("signalType1 "+signalType.name());
            System.out.println("emitResult1 "+emitResult.name());
            return false;           // True means retry
        });
//
//        sink.emitValue("hello", (signalType, emitResult) -> {
//            //Below will execute in case of an error like here when we are trying to emit multiple values
//            System.out.println("signalType2 "+signalType.name());
//            System.out.println("emitResult2 "+emitResult.name());
//            return false;  // Based on the value of signalType and emitResult we can decide to return true of false which determines if we want to retry or not
//        });
    }
}
