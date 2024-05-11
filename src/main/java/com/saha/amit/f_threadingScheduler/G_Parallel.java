package com.saha.amit.f_threadingScheduler;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
With parallel, we can make the publisher execute on different threads.
No of thread by default is dependent on no of CPU core we can override this by passing int argument .parallel(4)
However, we mush take care of execution part here for example we are adding items to arrayList
Which is not thread safe as a result we might not get correct result
With sequential() we can make is make it signe thread
 */
public class G_Parallel {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //List<Integer> vector= new Vector<>();
        Flux.range(1,1000)
                .parallel(4)                     //forcing it to be parallel
                .runOn(Schedulers.parallel())
                .doOnNext(i-> printThreadName("next "+ i))
                //.doOnNext(i-> vector.add(i))      //will have proper execution
                .doOnNext(i-> list.add(i))                  //can have incorrect execution
                .sequential()                                           //makes single thread
                .subscribe(j-> printThreadName("sub "+j ));

        Util.sleepSeconds(5);
        System.out.println(list.size());
        //System.out.println(vector.size());
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
