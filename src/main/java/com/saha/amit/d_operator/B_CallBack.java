package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class B_CallBack {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                    System.out.println("inside create");  //After doOnRequest
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    // fluxSink.complete();
                    fluxSink.error(new RuntimeException("oops"));
                    System.out.println("--completed");
                })
                .doOnComplete(() -> System.out.println("doOnComplete"))  // only when there is no error
                .doFirst(() -> System.out.println("doFirst1")) //do first execute bottom to up so this executes 2nd
                .doFirst(() -> System.out.println("doFirst2"))   //1st items that executes
                .doOnNext(o -> System.out.println("doOnNext : " + o))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe1" + s))  //next item before all do first
                .doOnSubscribe(s -> System.out.println("doOnSubscribe2" + s))  //do On Subscribe executes top to bottom, so this executes 2nd
                .doOnRequest(l -> System.out.println("doOnRequest : " + l))  //After subscribe
                .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))  //when there is an error
                .doOnTerminate(() -> System.out.println("doOnTerminate"))  // On complete or on error
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doFinally(signal -> System.out.println("doFinally 1 : " + signal))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))  // in case of take operation discarded elements
                .take(2)
                .doFinally(signal -> System.out.println("doFinally 2 : " + signal))  // always executes
                .subscribe(Util.subscriber());
    }
}
