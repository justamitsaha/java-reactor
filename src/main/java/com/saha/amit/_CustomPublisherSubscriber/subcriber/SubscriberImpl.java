package com.saha.amit._CustomPublisherSubscriber.subcriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public class SubscriberImpl implements Subscriber<String> {

    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        System.out.println("received: {} "+ email);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("error "+ throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("completed!");
    }
}