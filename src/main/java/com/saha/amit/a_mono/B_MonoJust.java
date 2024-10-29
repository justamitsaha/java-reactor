package com.saha.amit.a_mono;

import com.saha.amit._CustomPublisherSubscriber.subcriber.SubscriberImpl;
import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class B_MonoJust {
    public static void main(String[] args) {
        var mono = Mono.just("LOLO");

        System.out.println(mono);   //If we don't subscribe to mono it will not execute

        SubscriberImpl subscriber = new SubscriberImpl();
       mono.subscribe(subscriber);
       subscriber.getSubscription().request(10);


       /*
       We don't have to request the no of items with request(n) method
       like we do for custom  Subscriber as reactor framework does that for us
        */
        mono.subscribe(System.out::println);        //Only success

        mono.subscribe(                                             //Success, failure and error
                System.out::println,
                System.out::println,
                () -> System.out.println("completed")
        );
    }
}
