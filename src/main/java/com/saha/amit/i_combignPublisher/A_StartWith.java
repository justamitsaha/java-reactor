package com.saha.amit.i_combignPublisher;

import com.saha.amit.i_combignPublisher.helper.NameGenerator;
import com.saha.amit.util.Util;


public class A_StartWith {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generateName("Amit")
                .take(2)
                .subscribe(Util.subscriber("Amit"));

        nameGenerator.generateName("Jake")
                .take(2)
                .subscribe(Util.subscriber("Jake"));

        nameGenerator.generateName("Paul")
                .take(3)
                .subscribe(Util.subscriber("Paul"));

        nameGenerator.generateName("John")
                .take(3)
                .filter(s->s.startsWith("A"))
                .subscribe(Util.subscriber("John"));

    }

}
