package com.saha.amit.d_operator;

import com.saha.amit.d_operator.helper.Person;
import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class G_Transform {
    public static void main(String[] args) {

        getPerson()
                .transform(applyFilterMap())
                .subscribe(Util.subscriber());

    }

    public static Flux<Person> getPerson(){
        return Flux.range(1,10)
                .map(integer -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return personFlux -> personFlux
                .filter(person -> person.getAge()>18)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class,person -> System.out.println("Person not allowed "+ person.getName()+" of age "+ person.getAge()));

    }
}
