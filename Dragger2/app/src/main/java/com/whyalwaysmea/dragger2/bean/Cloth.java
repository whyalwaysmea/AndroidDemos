package com.whyalwaysmea.dragger2.bean;

/**
 * Created by Long
 * on 2016/12/14.
 */

public class Cloth {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }
}
