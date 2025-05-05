package com.saha.amit.e_hotColdPublisher;

import com.github.javafaker.Faker;
import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class G_MultipleSubscriber {

    public static void main(String[] args) {

        var generator = new NameGenerator();
        //var flux = Flux.create(generator);          //Cold publisher
        var flux = Flux.create(generator).share();      //Hot publisher
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        // somewhere else!
        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}

class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.faker().funnyName().name());
    }

}
