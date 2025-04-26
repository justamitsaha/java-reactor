package com.saha.amit.a_mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class A_StreamExample {
    private static final Logger log = LoggerFactory.getLogger(A_StreamExample.class);
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3);
        stream.map(s->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Map will not execute if we don't put any terminal operation
           log.info("Hello " +s);
            return s *2;
        }).forEach(integer -> log.info(String.valueOf(integer)));

        log.info("This ends here");
    }
}