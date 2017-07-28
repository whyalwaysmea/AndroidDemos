package com.whyalwaysmea.plugdemo;

import android.os.Environment;

/**
 * Created by HanLong on 2017/7/26.
 */

public class PluginCons {
    public static final String DexPath = Environment.getExternalStorageDirectory().toString() + "/whyalwaysmea/" + "plug.apk";
    public static final String PLUG_NAME = "com.whyalwaysmea.plug";

    public static final String COLOR = "color";
    public static final String DRAWABLE = "drawable";

    public static final String EXTRA_DEX_PATH = "extra.dex.path";
    public static final String EXTRA_CLASS = "extra.class";

    public static final String FLAG_ACTIVITY_FROM_PLUGIN = "FLAG_ACTIVITY_FROM_PLUGIN";
    public static final String FLAG_ACTIVITY_CLASS_NAME = "FLAG_ACTIVITY_CLASS_NAME";
}
