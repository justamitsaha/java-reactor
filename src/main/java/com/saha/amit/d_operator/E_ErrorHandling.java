package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
For error handling we can
1> either call onErrorReturn which will return default value
2>onErrorResume which will call a custom method for handling where we can put some dynamic logic if we want or something else
3>onErrorContinue where we can handle our logic or call some method, and it will not stop current execution
 */
public class E_ErrorHandling {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
//                .onErrorReturn(-1)                // returns default value of -1 in case of error
//                .onErrorResume(e -> fallback())   // implements fallback method to handel exception
                .onErrorContinue((err,obj)->{       // Takes 2 parameter error and the actual object
                    System.out.println("Error"+ err +" for "+ obj);//Handles exception and continues exception
                })
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
