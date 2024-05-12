package com.saha.amit.j_batching;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
Sometimes as part of your processing, you are looking for some kind of pattern in these events like  discard events of same type
With previous approach we can't do that as one event may be in previous list
Reactor provides a solution where we can add a skip param where we will recieve item like this where we can compare from previous
[event0, event1, event2]
[event1, event2, event3]
[event2, event3, event4]
 */
public class B_BufferTimeOut {

    public static void main(String[] args) {

        eventStreamNormalSteadyLoad()
                .buffer(3, 1)                              //[event0, event1, event2], [event1, event2, event3]
                //.buffer(3,2)                               //[event0, event1, event2], [event2, event3, event4]
                //.buffer(3,3)                               //[event0, event1, event2], [event3, event4, event5]
                // .buffer(3,5)                              //[event0, event1, event2], [event5, event6, event7]
                .subscribe(Util.subscriber("Steady Load"));
        Util.sleepSeconds(5);

    }

    private static Flux<String> eventStreamNormalSteadyLoad() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }
}
