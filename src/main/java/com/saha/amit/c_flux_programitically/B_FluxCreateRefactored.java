package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.function.Consumer;

public class B_FluxCreateRefactored {
    public static void main(String[] args) {
        FluxSinkProducer fluxSinkProducer = new FluxSinkProducer();
        Flux.create(fluxSinkProducer)
                .delayElements(Duration.ofMillis(500))
                .subscribe(System.out::println);

        //This is when flux will emit as next method is called here
        fluxSinkProducer.produce();
        Util.sleepSeconds(12);
    }
}

class FluxSinkProducer implements Consumer<FluxSink<String>> {
    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    //name can be anything for this method
    public void produce() {
        String name = "";
        int count = 0;
        do {
            name = Util.faker().gameOfThrones().character();
            count++;
            this.fluxSink.next(count + " --> " + name);
        } while (!name.equals("Muttering Bill") &&  count < 11);
        fluxSink.complete();

    }
}
