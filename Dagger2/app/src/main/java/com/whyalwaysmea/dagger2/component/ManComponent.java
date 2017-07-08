package com.whyalwaysmea.dagger2.component;

import com.whyalwaysmea.dagger2.ManActivity;

import dagger.Component;

/**
 * Created by HanLong on 2017/7/8.
 */
@Component
public interface ManComponent {
    void inject(ManActivity mainActivity);
}
