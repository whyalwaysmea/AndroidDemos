package com.whyalwaysmea.dragger2.bean;

/**
 * Created by Long
 * on 2016/12/14.
 */

public class ClothHandler {
    public Shoe handle(Cloth cloth) {
        return new Shoe(cloth);
    }
}
