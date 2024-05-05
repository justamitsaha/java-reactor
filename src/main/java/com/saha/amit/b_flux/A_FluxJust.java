package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*Flux,just() is used to create Flux when data is ready*/
public class A_FluxJust {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        flux
                // .delayElements(Duration.ofSeconds(1))     Force fully delay
                .subscribe(
                        System.out::println,                //s-> System.out.println(s)
                        System.out::println,                //e->System.out.println(e),
                        () -> System.out.println("completed")  //Complete signal
                );

        //Util.sleepSeconds(5);               //Needed when we are delaying above

        flux.subscribe(System.out::println);        // can subscribe to flux multiple times

        Flux.empty()                                //Empty Flux
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("completed Empty Flux") // Only complete
                );

    }
}
