package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class E_SubscribtionObject {
    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Sub : " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        //When we request item then only it will come here
                        System.out.println("onNext : " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError : " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

/*    If you see subscribe is the first thing it is getting invoked by the publisher.  It passes the subscription object, and
        we see that it receives the subscription, but no items are printed. Because we got the subscription, but we didn't
        request for any item. So subscriber has to request publisher. Hey publisher emit item. Then only the publisher will emit.
        So how come the previous examples, everything things were working is because it was things were handled.
        So we have to request for items So only when we UN-COMMENT BELOW CODE  we will receive item as we are explicitly  asking it*/

        Util.sleepSeconds(3);
        atomicReference.get().request(3);
        Util.sleepSeconds(3);

        /*
        Below code wil cause to emit all items hence complete will be called
        atomicReference.get().request(Long.MAX_VALUE);
        Util.sleepSeconds(20);
        */

        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        System.out.println("going to cancel");
        atomicReference.get().cancel();
        //after cancel below will not be called
        Util.sleepSeconds(3);
        atomicReference.get().request(4);

        Util.sleepSeconds(3);
    }
}

