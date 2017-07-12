package com.whyalwaysmea.dagger2.bean;

/**
 * Created by HanLong on 2017/7/12.
 */

public class People {
    private Car mCar;
    public People(Car car) {
        this.mCar = car;
    }

    public Car getCar() {
        return mCar;
    }
}
