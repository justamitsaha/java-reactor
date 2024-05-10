package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;
/*Here getMovie is a Hot publisher like a movie theatre, when first subscriber joins it starts emitting
It is achieved by adding share() method to Publisher*/
public class B_HotPublisherShared {
    public static void main(String[] args) {

        //Hot publisher - If one subscriber has already data published, then any new subscriber will miss out on the previously published data
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .share();

        movieStream.subscribe(Util.subscriber("JHOLU")); // When this joins it starts publishing
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("BHOLU")); // This subscciber misses some data
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("LOLU"));  // This subscciber misses some more data
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
