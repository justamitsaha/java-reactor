package com.saha.amit.c_flux_programitically;

import reactor.core.publisher.Flux;

public class C_TakeOperator {
    public static void main(String[] args) {
        Flux.range(1,10)
                .log()
                .take(3)
                .log()
                .subscribe(System.out::println);
    }
}
