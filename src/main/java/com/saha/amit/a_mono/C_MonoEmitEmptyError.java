package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class C_MonoEmitEmptyError {
    public static void main(String[] args) {
        getUser(3).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    /*This method shows different result thT can be sent from Mono
    It can return
    result --> onNext and onComplete will execute
    empty  --> onComplete will execute
    error --> onError will execute */
    public static Mono<String> getUser(int userId){
        if(userId == 1){
            return Mono.just(Util.faker().lordOfTheRings().character());
        } else if (userId ==2){
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not valid user"));
        }
    }
}
