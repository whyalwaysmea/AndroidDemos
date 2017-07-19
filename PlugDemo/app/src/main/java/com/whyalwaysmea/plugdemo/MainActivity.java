package com.whyalwaysmea.plugdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whyalwaysmea.plug.IShowToast;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dexOutputDir = getDir("dex1", 0);
        String dexPath = Environment.getExternalStorageDirectory().toString() + "/wtp/" + "output.jar";
        DexClassLoader loader = new DexClassLoader(dexPath,
                dexOutputDir.getAbsolutePath(),
                null, getClassLoader());
        try {
            Class clz = loader.loadClass("com.whyalwaysmea.plug.ShowToastImpl");
            IShowToast impl = (IShowToast) clz.newInstance();
            impl.showToast(this);
        } catch (Exception e) {
            Log.d("TEST111", "error happened", e);
        }
    }
}
