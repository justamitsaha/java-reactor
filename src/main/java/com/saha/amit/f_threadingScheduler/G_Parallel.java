package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class G_Parallel {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Flux.range(1,1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(i-> printThreadName("next "+ i))
                .doOnNext(i-> list.add(i))
                .subscribe(j-> printThreadName("sub "+j ));

        Util.sleepSeconds(10);
        System.out.println(list.size());
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
