package com.saha.amit.b_netty;


import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                //The repose will be in ByteBuffer which is like container of Flux of Bytes
                .responseContent()
                //Bytes changed to String
                .asString()
                .doOnNext(System.out::println)
                .next();
    }

}
