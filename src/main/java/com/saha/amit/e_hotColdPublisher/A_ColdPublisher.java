package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class A_ColdPublisher {

    //Cold publisher when new subscriber joins it receives new data from start no concept of missing out as the publishing has started by some other subscriber
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1));

        movieStream.subscribe(Util.subscriber("JHOLU"));
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("BHOLU"));
        Util.sleepSeconds(2);
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
