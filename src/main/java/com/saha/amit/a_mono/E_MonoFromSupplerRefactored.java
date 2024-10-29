package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/* Here we are moving our buisness logic in pipeline inside Mono
We can see that 
1 when we call the method with pipeline it executes imiddietly i.e. building pipeline happens quickly
2 When we subscribe to it is happens lazily but the method after that is waiting which is something not
expected for reactive programming i.e. it should not be blocking
3 When we call using subscribeOn(Schedulers.boundedElastic() then it behaves in a way expected by reactive 
programing. We have to keep a sleep method at end to prevent current thread from exiting otherwise the current
thread on whihc the program is running will exit without letting subscribe complete  */
public class E_MonoFromSupplerRefactored {
    public static void main(String[] args) {
        int i = 1;
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
                getName();                  // Waiting till the above is completed
                break;
            case 3:
                getName();
                getName()
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe(Util.onNext());
                getName();
                Util.sleepSeconds(3);       // Won't wait for above subscribe to complete
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
