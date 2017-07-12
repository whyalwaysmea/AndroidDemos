package com.whyalwaysmea.dagger2.component;

import com.whyalwaysmea.dagger2.ManActivity;
import com.whyalwaysmea.dagger2.module.CarModule;

import dagger.Component;

/**
 * Created by HanLong on 2017/7/8.
 */
@Component(modules = CarModule.class)
public interface ManComponent {
    void inject(ManActivity manActivity);
//    Car car();
}