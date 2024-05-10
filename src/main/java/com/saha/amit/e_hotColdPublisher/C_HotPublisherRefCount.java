package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;
/*
Another way to create hot publisher is using publish mehod and refCount
publish method returns Connectable flux and ref count tells the number of
subscribers need to join after which Publisher will start emitting
For share method it was default as 1
So shared() = .publish().refCount(1);
*/
public class C_HotPublisherRefCount {
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()                  // Publish returns a connectable flux through which multiple subscriber can connect
                .refCount(2);       //refCount denotes how many subscriber must join before publisher starts publishing, so here it waits for

        movieStream.subscribe(Util.subscriber("JHOLU"));
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("BHOLU"));     // since ref count is 2 once BHOLU joins then only it stars emitting
        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("LOLU"));       // misses out on some data
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
