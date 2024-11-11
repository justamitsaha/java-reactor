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
        //If there is no subscriber hot publisher will not emit as we can see once take ends subscription there is no emission
        Flux<String> movieStream = Flux.fromStream(B_HotPublisherShared::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .share();

        movieStream
                .take(3)
                .subscribe(Util.subscriber("JHOLU")); // When this joins it starts publishing
        Util.sleepSeconds(1);
        movieStream
                .take(3).
                subscribe(Util.subscriber("BHOLU")); // This subscriber misses some data
        Util.sleepSeconds(1);
        movieStream
                .take(3)
                .subscribe(Util.subscriber("LOLU"));  // This subscriber misses some more data
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
