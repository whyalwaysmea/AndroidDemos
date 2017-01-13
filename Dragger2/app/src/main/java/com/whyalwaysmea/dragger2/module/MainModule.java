package com.whyalwaysmea.dragger2.module;

import com.whyalwaysmea.dragger2.annotation.PerActivity;
import com.whyalwaysmea.dragger2.annotation.RedCloth;
import com.whyalwaysmea.dragger2.bean.Cloth;
import com.whyalwaysmea.dragger2.bean.ClothHandler;
import com.whyalwaysmea.dragger2.bean.Shoe;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long
 * on 2016/12/20.
 */
@Module
public class MainModule {

    public MainModule() {

    }

    /*@Provides
    public Shoe proiveShoe(@RedCloth Cloth cloth) {
        return new Shoe(cloth);
    }*/

    @Provides
    public Shoe handleShoe(@RedCloth Cloth cloth, ClothHandler clothHandler) {
        return  clothHandler.handle(cloth);
    }



    @Provides
    @RedCloth
    @PerActivity
    public Cloth provideCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    @Named("blue")
    public Cloth provideAnotherCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }
}
