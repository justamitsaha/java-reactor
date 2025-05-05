package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class H_Then {
    public static void main(String[] args) {

        var records = List.of("a", "b", "c");

            saveRecords(records)
                    // only in case of success
                    .then()
                    //.then(sendNotification(records))
                    .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> System.out.println("all these {} records saved successfully " + records));
    }
}
