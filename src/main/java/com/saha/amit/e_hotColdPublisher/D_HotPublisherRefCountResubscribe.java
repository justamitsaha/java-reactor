package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class D_HotPublisherRefCountResubscribe {
    public static void main(String[] args) {


        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        movieStream.subscribe(Util.subscriber("JHOLU"));

        //As ref count is 1 it emits on 1st subscription then thread waits meanwhile the 1st subscription end
        //So when new subscription is  received it is a new subscription from beginning and subscriber doesn't miss out
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
