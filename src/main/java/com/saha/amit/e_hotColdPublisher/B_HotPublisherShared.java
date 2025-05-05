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
                .doOnNext(s -> System.out.println("Generating "+ s))
                .delayElements(Duration.ofSeconds(1))
                .share();   // Converts to hot publisher
        Util.sleepSeconds(2);
        movieStream
                .take(3)
                .subscribe(Util.subscriber("SAM")); // When this joins it starts publishing
        Util.sleepSeconds(2);
        movieStream
                .take(5).
                subscribe(Util.subscriber("MIKE")); // This subscriber misses some data
        Util.sleepSeconds(10);

    }

    private static Stream<String> getMovie() {
        System.out.println("Generate movie stream");
        return Stream.iterate(1,i -> i+1)
                .map(integer -> "Scene "+integer);
    }
}
