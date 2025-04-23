package com.saha.amit._CustomPublisherSubscriber;

import com.saha.amit._CustomPublisherSubscriber.publisher.PublisherImpl;
import com.saha.amit._CustomPublisherSubscriber.subcriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
   1. publisher does not produce data unless subscriber requests for it.
   2. publisher will produce only <= subscriber requested items. publisher can also produce 0 items!
   3. subscriber can cancel the subscription. producer should stop at that moment as subscriber is no longer interested in consuming the data
   4. producer can send the error signal
 */


import java.time.Duration;

/*Creating a custom Publisher , Subscriber and Subscription Class
Write logic in Subscription to emit item in request method
Passing the Subscription object to Publisher
Passing the */
public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws InterruptedException {
        demo4();
    }

    /* Subscribed but nothing requested hence no result */
    private static void demo1(){
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }


    //After 10 items won't emit
    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        log.info("Request complete 1");
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        log.info("Request complete 2");
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        log.info("Request complete 3");
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        //Cancelling after this won't emit
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }


    //After error won't emit any item
    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        // Requesting 11 items will throw error so after this won't emit
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

}