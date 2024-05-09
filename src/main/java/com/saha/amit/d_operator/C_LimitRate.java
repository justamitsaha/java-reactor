package com.saha.amit.d_operator;

import com.saha.amit.util.Util;
import reactor.core.publisher.Flux;

/* Rate limit limts the rate at which the publisher publishes the data,
The first item tells the number of items pushlisher should publish first
The 2nd item tells by what percentage of data is send after which subsciber will request for next set.
(100,65) means pusblisher will send 100 items and when it reaches 65% of 100, subscriber  will request for next set
default is 75%. 
(10, 100 ) means publisher will send 10 items ad will default to 75%
(10, 0) means 10 items will send and after 100% consumption send more

*/

public class C_LimitRate {
    public static void main(String[] args) {
        Flux.range(1,20)
                .log()
                .limitRate(5)// 5 elements send first ,default is 75 % when 75% is reached it will request for new item
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
