package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class A_ColdPublisher {
    /*getMovie  is like a cold publisher like Netflix streaming , when ever anyone wants to see movie they subscribe, and it starts publishing for each subscriber
    Cold publisher when new subscriber joins it receives new data from start no concept of missing out as the publishing has started by some other subscriber*/
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(A_ColdPublisher::getMovie)
                .delayElements(Duration.ofSeconds(1));

        movieStream.subscribe(Util.subscriber("JHOLU"));        // Each will get stream separately from beginning
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("BHOLU"));        // Each will get stream separately from beginning
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("LOLU"));         // Each will get stream separately from beginning
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
