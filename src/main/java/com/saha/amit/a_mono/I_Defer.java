package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class I_Defer {

    public static void main(String[] args) {

        Mono.defer(I_Defer::createPublisher)
                .subscribe(Util.subscriber());

        createPublisher().subscribe(integer -> System.out.println());

    }

    // time-consuming publisher creation
    private static Mono<Integer> createPublisher(){
        System.out.println("creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    // time-consuming business logic
    private static int sum(List<Integer> list) {
        System.out.println("finding the sum of "+ list);
        Util.sleepSeconds(1);
        return list.stream().mapToInt(a -> a).sum();
    }
}
