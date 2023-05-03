package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

public class C_LimitRate {
    public static void main(String[] args) {
        Flux.range(1,20)
                .log()
                .limitRate(10)// default is 75 % when 75% is reached it will request for new item
                .subscribe(Util.onNext());

        Flux.range(1,20)
                .log()
                .limitRate(10,99)  //when 99% of data reached
                .subscribe(Util.onNext());

        Flux.range(1,20)
                .log()
                .limitRate(10,0)  //when 0 is used then it means 100% draining
                .subscribe(Util.onNext());
    }


}
