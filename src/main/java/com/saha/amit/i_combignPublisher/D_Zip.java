package com.saha.amit.i_combignPublisher;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class D_Zip {
    public static void main(String[] args) {
        Flux.zip(
                getBody(),
                getTyres(),
                getEngine()
        ).subscribe(Util.subscriber());

    }

    public static Flux<String> getBody(){
        return Flux.range(1,3)
                .map(integer -> "Body " +integer);
    }

    public static Flux<String> getEngine(){
        return Flux.range(1,2)
                .map(integer -> "Engine " +integer);
    }

    public static Flux<String> getTyres(){
        return Flux.range(1,3)
                .map(integer -> "Tyres " +integer);
    }
}
