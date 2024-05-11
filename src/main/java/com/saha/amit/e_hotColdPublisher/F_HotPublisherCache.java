package com.saha.amit.e_hotColdPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;
/* With cache the publisher will cache the entire response and provide to subscriber after the streaming ends
It will work even if we keep the publish() i.e. hot publisher and autoconect() i.e. not allowing new subscribption
Cache will store till Long.MAX_VALUE however we can override that by passing an numeric value
cache(2) will mean it will cache last 2 values from stream
*/
public class F_HotPublisherCache {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0)
                .cache();
                //.cache(2); //we can tell how many items to cache

        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("JHOLU"));    // Will get items from beginning

        Util.sleepSeconds(10);
        movieStream.subscribe(Util.subscriber("LOLU"));     // Will get all items at once even though subscription has ended
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
