package com.saha.amit.a_mono;

import com.saha.amit.util.Util;
import reactor.netty.http.client.HttpClient;

public class ReactiveHttpDemo {
    public static void main(String[] args) {
        HttpClient client = HttpClient.create();

        client.get()
                .uri("https://api.example.com/data")
                .responseContent()
                .aggregate()
                .asString()
                .subscribe(
                        response -> System.out.println("Received: " + response),
                        error -> System.err.println("Error: " + error)
                );

        // Block to see output (for demo only)
        Util.sleepSeconds(2);
    }
}
