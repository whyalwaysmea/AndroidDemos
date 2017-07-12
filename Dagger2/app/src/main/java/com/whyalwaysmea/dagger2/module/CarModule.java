package com.whyalwaysmea.dagger2.module;

import com.whyalwaysmea.dagger2.bean.Car;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HanLong on 2017/7/10.
 */
@Module
public class CarModule {

    @Provides
    @Named("red")
    Car redCarProvider() {
        return new Car("red");
    }

    @Provides
    @Named("blue")
    Car bludCarProvider() {
        return new Car("blue");
    }
}
