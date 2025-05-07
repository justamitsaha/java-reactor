package com.saha.amit.m_cotext.client;

import com.saha.amit.util.Util;
import reactor.util.context.Context;

public class D_ContextRateLimiterDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "mike"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);
    }

}