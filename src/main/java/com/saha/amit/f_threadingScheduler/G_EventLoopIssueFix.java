package com.saha.amit.f_threadingScheduler;

import com.saha.amit.b_netty.AbstractHttpClient;
import com.saha.amit.b_netty.ExternalServiceClient;
import com.saha.amit.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class G_EventLoopIssueFix extends AbstractHttpClient {

    public static void main(String[] args) {
        //demo1();
        demo2();

    }

    public static void demo1(){
        var client = new ExternalServiceClient();

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(G_EventLoopIssueFix::process)  // slows down execution
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);
    }

    public static void demo2(){
        G_EventLoopIssueFix test = new G_EventLoopIssueFix();
        for (int i = 1; i <= 5; i++) {
            test.getProductName(i)
                    .map(G_EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);
    }


    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next()
                .publishOn(Schedulers.boundedElastic());  //switches the downstream to a dedicated thread pool
    }

    private static String process(String input){
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
