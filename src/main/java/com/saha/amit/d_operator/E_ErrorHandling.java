package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

/*
For error handling we can
1> either call onErrorReturn which will return default value
2>onErrorResume which will call a custom method for handling where we can put some dynamic logic if we want or something else
3>onErrorContinue where we can handle our logic or call some method, and it will not stop current execution
 */
public class E_ErrorHandling {
    public static void main(String[] args) {
        //errorEmission();
        //onErrorReturn();
        //onErrorReturnSpecific();
        //onErrorResume();
        //onErrorComplete();
        onErrorContinue();
    }

    public static void errorEmission() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .subscribe(System.out::println);
    }

    public static void onErrorReturn() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorReturn(-1)      //If placed before map won't work, more effective just before subscribe
                .subscribe(System.out::println);
    }

    public static void onErrorReturnSpecific() {
        Flux.range(1, 5)
                .map(s -> {
                    int i =new Random().nextInt(1, 4);
                    switch (i){
                        case 1 -> throw new IllegalArgumentException();
                        case 2 -> throw new RuntimeException();
                        default -> {
                            return i;
                        }
                    }
                })
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(RuntimeException.class, -2)
                .subscribe(System.out::println);
    }

    public static void onErrorResume(){
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorResume(e -> fallback())   // implements fallback method to handel exception
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }

    public static void onErrorComplete() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    public static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorContinue((err, obj) -> {       // Takes 2 parameter error and the actual object
                    System.out.println("Error" + err + " for " + obj);//Handles exception and continues exception
                })
                .subscribe(Util.subscriber());
    }


}
