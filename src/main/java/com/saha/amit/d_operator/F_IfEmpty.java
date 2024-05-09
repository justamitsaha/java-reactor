package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/*
If the source is not emmiting any signle data then we can take user to
1- defaultIfEmpty --> Returns default value
2- switchIfEmpty  --> Call a fallback method to return the response
But both will take effect only when source is not emmiting any data
One good use case redis cache if empty
*/
public class F_IfEmpty {
    public static void main(String[] args) {
        getOrderNumber()
                .filter(i->i>10)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());

        getOrderNumber()
                .filter(i->i>10)
                .switchIfEmpty(fallBack()) //If the main method provides even one response it will not go to fallback
                .subscribe(Util.subscriber());

    }

    public static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 5);
    }

    public static Flux<Integer> fallBack() {
        return Flux.range(1, 12);
    }
}
