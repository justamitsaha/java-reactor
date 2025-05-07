package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


/*
Sinks.many().uniCast()  is used to create Flux which can have many subscribers
 */
public class B_SinkManyUniCast {
    public static void main(String[] args) {
        //demo1();
        demo2();
    }

    private static void demo1(){
        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
    }

    // we can not have multiple subscribers
    private static void demo2(){
        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
    }
}
