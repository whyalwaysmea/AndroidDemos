package com.whyalwaysmea.dragger2.bean;

/**
 * Created by Long
 * on 2016/12/14.
 */

public class Shoe {

    private Cloth mCloth;

    public Shoe(Cloth cloth) {
        this.mCloth = cloth;
    }

    public Cloth getCloth() {
        return this.mCloth;
    }

    @Override
    public String toString() {
        return "鞋子" + "----布料是:" + mCloth.toString();
    }
}
