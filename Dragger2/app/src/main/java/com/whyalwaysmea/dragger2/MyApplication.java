package com.whyalwaysmea.dragger2;

import android.app.Application;

import com.whyalwaysmea.dragger2.component.BaseComponent;
import com.whyalwaysmea.dragger2.component.DaggerBaseComponent;
import com.whyalwaysmea.dragger2.module.BaseModule;

/**
 * Created by Long
 * on 2016/12/14.
 */

public class MyApplication extends Application {

    private BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseComponent = DaggerBaseComponent.builder()
                .baseModule(new BaseModule())
                .build();

    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }
}
