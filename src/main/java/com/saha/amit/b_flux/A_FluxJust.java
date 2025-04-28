package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*Flux,just() is used to create Flux when data is ready*/
public class A_FluxJust {

    private static final Logger log = LoggerFactory.getLogger(A_FluxJust.class);
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 6);
        flux.subscribe(integer -> log.info(String.valueOf(integer)));
        flux                                                    //can subscribe to flux multiple times
                .delayElements(Duration.ofSeconds(1))           //Force fully delay
                .subscribe(
                        s -> log.info(String.valueOf(s)),
                        e -> log.error(String.valueOf(e)),
                        () -> log.info("completed")
                );

        Util.sleepSeconds(5);               //Needed when we are delaying above

        Flux.empty()                                //Empty Flux
                .subscribe(
                        s -> log.info((String) s),
                        e -> log.error(String.valueOf(e)),
                        () -> log.info("completed Empty Flux") // Only complete
                );

    }
}
