package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class E_HotPublisherAutoConnect {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0);
                //.autoConnect(1);

        //With hot subscriber streaming will start even without any subscription as minimum Subscriber is 0
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("JHOLU"));

        //With auto connect it will not allow 2nd subscriber to re-subscribe after 1sr subscription has ended
        Util.sleepSeconds(10);
        movieStream.subscribe(Util.subscriber("LOLU"));
        Util.sleepSeconds(10);

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
