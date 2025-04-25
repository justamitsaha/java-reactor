package com.saha.amit.b_netty;

import com.saha.amit.util.Util;

public class Lec11NonBlockingIO {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        System.out.println("starting");
        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}