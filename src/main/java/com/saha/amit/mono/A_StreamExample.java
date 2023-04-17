package com.saha.amit.mono;

import java.util.stream.Stream;

public class A_StreamExample {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3);
        stream.map(s->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Map will not execute if we don't put any terminal operation
            System.out.println("Hello " +s);
            return s *2;
        }).forEach(System.out::println);

        System.out.println("This ends here");
    }
}