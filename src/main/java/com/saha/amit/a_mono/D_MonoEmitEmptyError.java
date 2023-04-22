package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;

public class D_MonoEmitEmptyError {
    public static void main(String[] args) {
        getUser(1).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

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
