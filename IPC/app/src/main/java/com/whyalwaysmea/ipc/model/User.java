package com.whyalwaysmea.ipc.model;

import java.io.Serializable;

/**
 * Created by Long
 * on 2016/11/11.
 */

public class User implements Serializable {
    public String name;
    public int age;

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

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
