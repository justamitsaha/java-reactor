package com.saha.amit.d_operator;

import com.saha.amit.d_operator.helper.OrderService;
import com.saha.amit.d_operator.helper.UserService;
import com.saha.amit.util.Util;

public class I_Flatmap {
    public static void main(String[] args) {


        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // mono / flux
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }
}
