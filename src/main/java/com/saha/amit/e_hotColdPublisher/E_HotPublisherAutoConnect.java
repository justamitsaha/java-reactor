package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/* With auto connect option publisher will start emitting even when there is no subscriber
And once the publisher completes it will not publish again even if someone new joins
*/
public class E_HotPublisherAutoConnect {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0);
                //.autoConnect(1);      // with autoConnect(1) first subscriber won't miss

        
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("JHOLU"));    // Publisher has started, so it will miss out on few items

        Util.sleepSeconds(10);
        System.out.println("Subsciber2 about to subscribe");
        movieStream.subscribe(Util.subscriber("LOLU"));     // This is miss out on all data as publisher has completed

    }

    private static Stream<String> getMovie() {
        System.out.println("Generate movie stream");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }
}
