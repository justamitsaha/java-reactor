package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/* Here we are moving our business logic in pipeline inside Mono
We can see that 
1 when we call the method with pipeline it executes immediately i.e. building pipeline happens quickly
2 When we subscribe to it is happens lazily but the method after that is waiting which is something not
expected for reactive programming i.e. it should not be blocking
3 When we call using subscribeOn(Schedulers.boundedElastic() then it behaves in a way expected by reactive 
programing. We have to keep a sleep method at end to prevent current thread from exiting otherwise the current
thread on which the program is running will exit without letting subscribe complete  */
public class F_MonoFromSupplerRefactored {
    private static final Logger log = LoggerFactory.getLogger(F_MonoFromSupplerRefactored.class);
    public static void main(String[] args) {
        int i = 3;
        switch (i) {
            case 1 -> {
                getName();
                getName();
            }
            case 2 -> {
                getName();
                getName().subscribe(
                        Util.onNext()
                );
                getName();                  // Waiting till the above is completed
            }
            case 3 -> {
                getName();
                getName()
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe(Util.onNext());
                getName();
                Util.sleepSeconds(3);       // Won't wait for above subscribe to complete
            }
        }
    }

    public static Mono<String> getName() {
        log.info("Started getName");
        return Mono.fromSupplier(() -> {
            log.info("Generating Name");
            Util.sleepSeconds(2);
            return Util.faker().harryPotter().character();
        }).map(String::toUpperCase);
    }
}
