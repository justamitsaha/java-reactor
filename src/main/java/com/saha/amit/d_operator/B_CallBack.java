package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class B_CallBack {

    public static void main(String[] args) {
        System.out.println();

        //Code will execute from top to bottom but subscriber operations execute from bottom to top for eg. doFirst, doOnRequest,
        Flux.<Integer>create(fluxSink -> {
                    System.out.println("producer begins");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                    // fluxSink.error(new RuntimeException("oops"));
                    System.out.println("producer ends");
                })
                .doOnComplete(() -> System.out.println("doOnComplete-1"))    //When producer sends complete signal, will not execute with error
                .doFirst(() -> System.out.println("doFirst-1"))          //very first time before anything start between producer and subscriber
                .doOnNext(item -> System.out.println("doOnNext-1 " + item)) // For every emitted item, it can mutate items
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe-1 " + subscription)) // When subscription object is passed downstream by publisher
                .doOnRequest(request -> System.out.println("doOnRequest-1 " + request)) // When downstream send request// this will have 2 due to take operation
                .doOnError(error -> System.out.println("doOnError-1 " + error.getMessage()))  // When error signal is passed
                .doOnTerminate(() -> System.out.println("doOnTerminate-1")) // complete or error case, combination of error and complete
                .doOnCancel(() -> System.out.println("doOnCancel-1"))  // When downstream cancels request
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard-1 " + o))  // When producer produced item but subscriber didn't receive, or it cancelled before receiving
                .doFinally(signal -> System.out.println("doFinally-1 " + signal)) // finally irrespective of the reason
                .take(2)
                .doOnComplete(() -> System.out.println("doOnComplete-2"))
                .doFirst(() -> System.out.println("doFirst-2"))
                .doOnNext(item -> System.out.println("doOnNext-2 " + item))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe-2 " + subscription))
                .doOnRequest(request -> System.out.println("doOnRequest-2 " + request))
                .doOnError(error -> System.out.println("doOnError-2 " + error.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate-2")) // complete or error case
                .doOnCancel(() -> System.out.println("doOnCancel-2"))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard-2 " + o))
                .doFinally(signal -> System.out.println("doFinally-2 " + signal)) // finally irrespective of the reason
                //.take(4)
                .subscribe(Util.subscriber("subscriber"));


    }
}
