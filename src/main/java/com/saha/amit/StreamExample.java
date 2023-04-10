package com.saha.amit;

import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3);
        stream.map(s->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Things inside map will not be printed if we remove terminal operation
            System.out.println("Hello " +s);
            return s *2;
        }).forEach(s-> System.out.println());

        System.out.println("This ends here");
    }
}