package com.saha.amit.b_flux;

import com.saha.amit.util.Util;

public class F_FluxVsList {
    public static void main(String[] args) {

        //Blocking all items will be returned at the end of 5 seconds
        Util.getNamesList(5).forEach(System.out::println);

        /*Non Blocking, it will also take 5 seconds but as and when items will be available it wille emit
        and if we need to process the item we can process it*/
        Util.getNamesFlux(5).subscribe(Util.onNext());

    }
}
