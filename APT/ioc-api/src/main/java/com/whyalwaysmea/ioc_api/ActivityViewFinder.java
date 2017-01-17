package com.whyalwaysmea.ioc_api;

import android.app.Activity;
import android.view.View;

/**
 * Created by Long
 * on 2017/1/16.
 */

public class ActivityViewFinder implements ViewFinder {
    @Override
    public View findView(Object object, int id) {
        return ((Activity) object).findViewById(id);
    }
}
