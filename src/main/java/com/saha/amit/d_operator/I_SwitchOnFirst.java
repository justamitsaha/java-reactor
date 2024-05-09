package com.saha.amit.d_operator;

import com.saha.amit.d_operator.helper.Person;
import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
Switch on first will sit between publisher and subscriber, and it will get the first item.
On the basis of the first item it can check any condition e.g. format and take decision
weather to emit the item or  route it to process it differently
If first item is ok it will not check other items,
If first item is not passing condition it will check all items
 */
public class I_SwitchOnFirst {

    public static void main(String[] args) {

        getPerson()
                .switchOnFirst((signal, personFlux) -> {
                    return signal.isOnNext() && signal.get().getAge() > 10 ? personFlux : applyFilterMap().apply(personFlux);
                })
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
