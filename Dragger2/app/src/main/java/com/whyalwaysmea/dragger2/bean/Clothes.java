package com.whyalwaysmea.dragger2.bean;

import javax.inject.Inject;

/**
 * Created by Long
 * on 2016/12/14.
 */

public class Clothes {
    private Cloth cloth;

    @Inject
    public Clothes() {
        Cloth cloth = new Cloth();
        cloth.setColor("白色");
        this.cloth = cloth;
    }

    public Clothes(Cloth cloth) {
        this.cloth = cloth;
    }



    public Cloth getCloth() {
        return cloth;
    }

    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }
}
