package com.saha.amit.d_operator.helper;

import com.saha.amit.util.Util;



public class Person {

    private String name;
    private int age;

    public Person(){
        this.name = Util.faker().funnyName().name();
        this.age = Util.faker().random().nextInt(1, 30);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
