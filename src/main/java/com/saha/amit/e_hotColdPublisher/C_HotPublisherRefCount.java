package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class C_HotPublisherRefCount {
    public static void main(String[] args) {

        //shared() = .publish().refCount(1);
        //refCount denotes how many subscriber must join before publisher starts publishing, so here it waits for
        //2 publisher so first 2 don't miss out
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(2);

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
