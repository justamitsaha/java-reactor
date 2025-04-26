package com.saha.amit.a_mono;

import com.saha.amit._CustomPublisherSubscriber.subcriber.SubscriberImpl;
import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class B_MonoJust {
    private static final Logger log = LoggerFactory.getLogger(B_MonoJust.class);
    public static void main(String[] args) {
        var mono = Mono.just("LOLO");
        log.info(String.valueOf(mono));   //Will not execute Mono

        /*
       We don't have to request the no of items with request(n) method
       like we do for custom  Subscriber as reactor framework does that for us with Long.MAX_VALUE (unbounded demand)
        */
        mono.subscribe(System.out::println);

        mono.subscribe(
                System.out::println,        //Success,
                System.out::println,        //failure and error
                () -> log.info("completed") //Completed
        );


        mono.subscribe(
                value -> log.info("Received: "+ value),
                error -> System.err.println("Error: "+ error),
                () -> log.info("Completed"),
                subscription -> subscription.request(1)  // Manually request 1 item
        );

        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);






    }
}
