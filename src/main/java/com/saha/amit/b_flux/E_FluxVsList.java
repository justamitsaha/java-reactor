package com.saha.amit.b_flux;

import com.saha.amit.util.Util;

public class E_FluxVsList {
    public static void main(String[] args) {

        Util.getNamesList(5).forEach(System.out::println);

        Util.getNamesFlux(5).subscribe(Util.onNext());

    }
}
