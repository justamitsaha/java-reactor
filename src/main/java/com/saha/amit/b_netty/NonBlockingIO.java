package com.saha.amit.b_netty;

import com.saha.amit.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");
        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}