package com.saha.amit.c_flux_programitically;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class B_FluxCreateRefactored {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer).subscribe(System.out::println);
    }
}

class NameProducer implements Consumer<FluxSink<String>> {
    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }
    public void produce(){
        String name = Util.faker().gameOfThrones().character();
        this.fluxSink.next(name);
    }
}
