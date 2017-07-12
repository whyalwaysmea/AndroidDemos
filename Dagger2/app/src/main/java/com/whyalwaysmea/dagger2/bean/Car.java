package com.whyalwaysmea.dagger2.bean;

/**
 * Created by HanLong on 2017/7/8.
 */

public class Car {
    public Engine mEngine;
    public String mColor;
    public Car(Engine engine) {
        this.mEngine = engine;
    }

    public Car(String color) {
        this.mColor = color;
    }

    public Engine getEngine() {
        return mEngine;
    }

    public String getColor() {
        return mColor;
    }
}
