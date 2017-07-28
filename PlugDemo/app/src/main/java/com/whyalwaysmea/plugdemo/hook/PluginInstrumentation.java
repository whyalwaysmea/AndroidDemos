package com.whyalwaysmea.plugdemo.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.whyalwaysmea.plugdemo.PluginCons;

/**
 * Created by HanLong on 2017/7/27.
 */

public class PluginInstrumentation extends Instrumentation {
    private boolean isPlugin;


    public PluginInstrumentation(Instrumentation instrumentation) {

    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (intent != null) {
            isPlugin = intent.getBooleanExtra(PluginCons.FLAG_ACTIVITY_FROM_PLUGIN, false);
        }
        if (isPlugin && intent != null) {
            className = intent.getStringExtra(PluginCons.FLAG_ACTIVITY_CLASS_NAME);
        }
        return super.newActivity(cl, className, intent);
    }
}
