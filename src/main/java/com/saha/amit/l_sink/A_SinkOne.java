package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*Sink has 2 parts one emits one subscribes*/
public class A_SinkOne {
    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo3();

    }

    public static void demo1(){
        Sinks.One<Object> sink = Sinks.one(); // From this we will emit a value
        Mono<Object> mono = sink.asMono();  //From this we will subscribe
        mono.subscribe(Util.subscriber("SAM"));
        sink.tryEmitValue("HI");
        sink.tryEmitValue("CHU<-->CHU");        // When we try to emit another value nothing happens as the sink is closed
        //sink.tryEmitEmpty();                                      // For empty
        //sink.tryEmitError(new RuntimeException());    //For error
        Util.sleepSeconds(2);
    }

    public static void demo2(){
        Sinks.One<Object> sink = Sinks.one(); // From this we will emit a value
        Mono<Object> mono = sink.asMono();  //From this we will subscribe
        mono.subscribe(Util.subscriber("SAM"));
        mono.subscribe(Util.subscriber("MIKE"));
        sink.tryEmitValue("HI");
        Util.sleepSeconds(2);
    }

    // emit failure handler - we can not emit after complete
    private static void demo3(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"));

        sink.emitValue("hi", ((signalType, emitResult) -> {
            System.out.println("hi");
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }));

        sink.emitValue("hello", ((signalType, emitResult) -> {
            System.out.println("hello");
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;  // If we make it true it will go in a loop trying again and again
        }));
    }
}
