package com.saha.amit.b_flux;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class C_FluxFromStream {
    public static void main(String[] args) {
        List<String> list = List.of("abs", "bbs", "ccs");
        Stream<String> stream = list.stream();

        //Flux from Stream
        Flux<String> flux = Flux.fromStream(stream);
        //Flux<String> flux = Flux.fromStream(() -> list.stream());
        flux.subscribe(
                s -> System.out.println(s),
                e -> System.out.println(e),
                () -> System.out.println("completed stream 1")
        );

        flux.subscribe(s -> System.out.println(s));             // Since this flux is from stream we can't subscribe again as stream is closed

        Map<String, String> map = Map.of(
                Util.faker().funnyName().name(), Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().funnyName().name(), Util.faker().howIMetYourMother().catchPhrase(),
                Util.faker().funnyName().name(), Util.faker().howIMetYourMother().catchPhrase()
        );

        /*Since Map doesn't implement iterable we have to create a Stream and then convert it in to a Flux
        * */
        Stream<Map.Entry<String, String>> stream1 = map.entrySet().stream();
        Flux.fromStream(stream1).subscribe(
                s -> System.out.println("stream1 "+s),
                e -> System.out.println(e),
                () -> System.out.println("Stream 2 completed")
        );


    }
}
