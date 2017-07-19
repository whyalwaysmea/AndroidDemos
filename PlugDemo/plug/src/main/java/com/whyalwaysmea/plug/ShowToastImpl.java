package com.whyalwaysmea.plug;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HanLong on 2017/7/19.
 */

public class ShowToastImpl implements IShowToast {

    @Override
    public int showToast(Context context) {
        Toast.makeText(context, "我来自另一个dex文件", Toast.LENGTH_LONG).show();
        return 100;
    }
}
