package com.whyalwaysmea.ioc_api;

/**
 * Created by Long
 * on 2017/1/16.
 */

public interface ViewBinder<T> {
    void bindView(T host, Object object, ViewFinder finder);

    void unBindView(T host);
}
