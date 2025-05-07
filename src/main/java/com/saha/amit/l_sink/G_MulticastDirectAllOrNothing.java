package com.saha.amit.l_sink;

import com.saha.amit.util.Util;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class G_MulticastDirectAllOrNothing {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().directAllOrNothing();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            System.out.println("item: " + i + "result: " + result);
        }
    }
}
