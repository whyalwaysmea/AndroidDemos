package com.whyalwaysmea.dagger2.module;

import com.whyalwaysmea.dagger2.bean.Car;
import com.whyalwaysmea.dagger2.bean.People;
import com.whyalwaysmea.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HanLong on 2017/7/12.
 */
@Module
public class PeopleModule {
    @Provides
    @ActivityScope
    People peopleProvider(Car car) {
        return new People(car);
    }
}
