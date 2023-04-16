package com.saha.amit;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoFromSupplerRefactored {
    public static void main(String[] args) {
        int i = 3;
        switch (i) {
            case 1:
                getName();
                getName();
                break;
            case 2:
                getName();
                getName().subscribe(
                        Util.onNext()
                );
                getName();
                break;
            case 3:
                getName();
                getName().subscribeOn(Schedulers.boundedElastic());
                getName();
        }
    }

    public static Mono<String> getName() {
        System.out.println("Started getName");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating Name");
            Util.sleepSeconds(2);
            return Util.faker().harryPotter().character();
        }).map(String::toUpperCase);
    }
}
