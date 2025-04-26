package com.saha.amit.a_mono;

import com.github.javafaker.Faker;
import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class E_MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(E_MonoFromRunnable.class);
    public static void main(String[] args) {

        getProductName(2).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

    }

    private static Mono<String> getProductName(int productId) {
        if (productId == 1) {
            // Product exists → Emit its name (lazy)
            return Mono.fromSupplier(() -> Faker.instance().commerce().productName());
        } else {
            // Product missing → Notify business + emit complete Signal (lazy)
            return Mono.fromRunnable(() -> notifyBusinessForMissingProduct(productId));
        }
    }

    private static void notifyBusinessForMissingProduct(int productId) {
        log.info("Log the missing product");
    }
}
