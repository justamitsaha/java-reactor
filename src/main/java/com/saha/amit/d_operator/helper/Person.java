package com.saha.amit.d_operator.helper;

import com.saha.amit.util.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private String name;
    private int age;

    public Person(){
        this.name = Util.faker().funnyName().name();
        this.age = Util.faker().random().nextInt(1, 30);
    }


}
