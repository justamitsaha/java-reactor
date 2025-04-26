package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class D_MonoWithSupplierCallable {
    private static final Logger log = LoggerFactory.getLogger(D_MonoWithSupplierCallable.class);
    public static void main(String[] args) {

        /*
        1> Just should be used when data is ready
        2> When we call getName with Just getName will get called even without subscribing
         */
        Mono<String> mono = Mono.just(getName());

        //When data is not ready should use Supplier
        Mono<String> mono1 = Mono.fromSupplier(D_MonoWithSupplierCallable::getName);
        mono1.subscribe(
                Util.onNext()
        );

        //Another way for declaring supplier same can be done with callable
        Supplier<String> supplier = D_MonoWithSupplierCallable::getName;
        Mono<String> mono4 = Mono.fromSupplier(supplier);

        //If the methods throw exception like getName2  then we can use callable
        //if we use Supplier then we have to use try catch
        Callable<String> callable = D_MonoWithSupplierCallable::getName2;
        Mono<String> mono2= Mono.fromCallable(callable);
        mono2.subscribe(
                Util.onNext()
        );

    }

    public static String getName(){
        Util.sleepSeconds(2);
        String name = Util.faker().lordOfTheRings().character();
        log.info("Generating name "+ name);
        return name;
    }

    public static String getName2() throws Exception{
        Util.sleepSeconds(2);
        String name = Util.faker().lordOfTheRings().character();
        log.info("Generating name "+ name);
        return name;
    }
}
