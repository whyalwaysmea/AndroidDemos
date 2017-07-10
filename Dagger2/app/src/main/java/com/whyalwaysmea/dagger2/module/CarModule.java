package com.whyalwaysmea.dagger2.module;

import com.whyalwaysmea.dagger2.bean.Car;
import com.whyalwaysmea.dagger2.bean.Engine;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HanLong on 2017/7/10.
 */
@Module
public class CarModule {
    @Provides
    Car carProvide(Engine engine) {
        return new Car(engine);
    }
}
