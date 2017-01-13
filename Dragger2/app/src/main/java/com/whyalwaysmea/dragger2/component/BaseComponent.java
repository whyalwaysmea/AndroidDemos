package com.whyalwaysmea.dragger2.component;

import com.whyalwaysmea.dragger2.bean.ClothHandler;
import com.whyalwaysmea.dragger2.module.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long
 * on 2016/12/20.
 */
@Component(dependencies = BaseModule.class)
@Singleton
public interface BaseComponent {
    ClothHandler getClothHandler();
}
