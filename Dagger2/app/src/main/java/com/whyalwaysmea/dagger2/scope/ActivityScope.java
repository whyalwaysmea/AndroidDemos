package com.whyalwaysmea.dagger2.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by HanLong on 2017/7/12.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
