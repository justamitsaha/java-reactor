package com.saha.amit.c_flux_programitically;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class G_FluxGenerateState {
    public static void main(String[] args) {
        approach1();
    }

    public static void approach1() {
        AtomicInteger counter = new AtomicInteger(0); // 🚨 Problem: Mutable shared state
        Flux.generate(sink -> {
            String country = Faker.instance().country().name();
            sink.next(country);

            if (country.equalsIgnoreCase("Canada") ||
                    counter.incrementAndGet() >= 10) {
                sink.complete();
            }
        }).subscribe(System.out::println);
    }

    public static void approach2() {
        Flux.generate(
                () -> 0,                    // Initial state supplier[0] will hold our counter
                (counter, sink) -> {
                    String country = Faker.instance().country().name();
                    sink.next(country);
                    counter++;             // Increment counter
                    if (country.equalsIgnoreCase("Canada") || counter >= 10) {
                        sink.complete();
                    }
                    return counter;         // Return updated state
                }
        );
    }
}
