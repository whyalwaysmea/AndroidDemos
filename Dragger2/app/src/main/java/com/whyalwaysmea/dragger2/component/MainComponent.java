package com.whyalwaysmea.dragger2.component;

import com.whyalwaysmea.dragger2.MainActivity;
import com.whyalwaysmea.dragger2.annotation.PerActivity;
import com.whyalwaysmea.dragger2.module.MainModule;

import dagger.Component;

/**
 * Created by Long
 * on 2016/12/20.
 */
@Component(modules = MainModule.class, dependencies = BaseComponent.class)
@PerActivity
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
