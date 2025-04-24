package com.saha.amit.a_mono;

import com.saha.amit._CustomPublisherSubscriber.subcriber.SubscriberImpl;
import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class B_MonoJust {
    public static void main(String[] args) {
        var mono = Mono.just("LOLO");
        System.out.println(mono);   //If we don't subscribe to mono it will not execute

        /*
       We don't have to request the no of items with request(n) method
       like we do for custom  Subscriber as reactor framework does that for us with Long.MAX_VALUE (unbounded demand)
        */
        mono.subscribe(System.out::println);

        mono.subscribe(
                System.out::println,        //Success,
                System.out::println,        //failure and error
                () -> System.out.println("completed") //Completed
        );


        mono.subscribe(
                value -> System.out.println("Received: "+ value),
                error -> System.err.println("Error: "+ error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)  // Manually request 1 item
        );

        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);






    }
}
