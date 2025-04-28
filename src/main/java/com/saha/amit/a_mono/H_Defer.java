package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class H_Defer {


    private static final Logger log = LoggerFactory.getLogger(H_Defer.class);
    public static void main(String[] args) {

//        Mono.defer(H_Defer::createPublisher);
        Mono.defer(H_Defer::createPublisher).subscribe(Util.subscriber());
//
//        createPublisher().subscribe(integer -> System.out.println());
        Util.sleepSeconds(5);

    }

    // time-consuming publisher creation
    private static Mono<Integer> createPublisher() {
        log.info("creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(1);
        return sum(list);
    }

    // time-consuming business logic
    private static Mono<Integer> sum(List<Integer> list) {
        log.info("finding the sum of " + list);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> {
            log.info("Starting calculation");
            int count = 0;
            for (int i : list) {
                count = count + i;
            }
            return count;
        });
    }
}
