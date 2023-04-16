package com.saha.amit;

import com.saha.amit.util.Util;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureMono {
    public static void main(String[] args) {

    }

    public static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> {
            Util.sleepSeconds(2);
            return Util.faker().lordOfTheRings().character();
        });
    }
}
