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


        mono.subscribe(System.out::println);        //Only success

        mono.subscribe(                                             //Success, failure and error
                System.out::println,
                System.out::println,
                () -> System.out.println("completed")
        );

        Mono<Integer> mono2 = Mono.just("ball")
                .map(s -> s.length()/0);
        //If we don't handel error and error happens the exception will be thrown
        mono2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
