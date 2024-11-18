package com.saha.amit.k_repeatRetry;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class A_Repeat {

    //Can do this with multiple subcribe of for loop also but then it won't gaurantee that it will happen sequentially and 
    //after the subcribe is complete. Repeat will happen only once the subscribe is complete
    public static void main(String[] args) {
        getInteger()
                .repeat(2)                  //After complete will repeat 2 times
                .subscribe(Util.subscriber());
    }

    private static Flux<String> getInteger() {
        return Flux.just(Util.faker().howIMetYourMother().catchPhrase())
                .doOnSubscribe(s -> System.out.println("Subscribed "))
                .doOnComplete(() -> System.out.println("Completed"));

    }
}
