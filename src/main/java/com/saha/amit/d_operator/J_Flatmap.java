package com.saha.amit.d_operator;

import com.saha.amit.d_operator.helper.OrderService;
import com.saha.amit.d_operator.helper.UserService;
import com.saha.amit.util.Util;
/*
When we are trying to transform using map, and we call a function which also returns a Flux or Mono
In that case map will not work as it will return a flux which we can't understand
In those scenarios we need flatMap which will flatten the underlying Flux/Mono to Objects
 */
public class J_Flatmap {
    public static void main(String[] args) {

        UserService.getUsers()
                .map(user -> OrderService.getOrders(user.getUserId())) //  OrderService.getOrders returns a flux so map will return a flux like FluxConcatMapNoPrefetch
                .subscribe(Util.subscriber());


        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // We need flatMap to make sense of the returned flux
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }
}
