package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class G_FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(
                    s-> System.out.println(s)
                );

        Util.sleepSeconds(10);
    }
}
