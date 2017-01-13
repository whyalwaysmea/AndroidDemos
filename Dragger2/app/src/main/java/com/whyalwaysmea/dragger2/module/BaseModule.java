package com.whyalwaysmea.dragger2.module;

import com.whyalwaysmea.dragger2.bean.ClothHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long
 * on 2016/12/20.
 */
@Module
public class BaseModule {
    @Provides
    @Singleton
    public ClothHandler getClothHandler() {
        return new ClothHandler();
    }
}
