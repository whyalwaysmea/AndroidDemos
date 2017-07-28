package com.whyalwaysmea.plugdemo.proxy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whyalwaysmea.plugdemo.PluginCons;
import com.whyalwaysmea.plugdemo.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * 代理Activity
 */
public class ProxyActivity extends AppCompatActivity {

    private static final String TAG = "ProxyActivity";

    public static final String FROM = "extra.from";
    public static final int FROM_EXTERNAL = 0;
    public static final int FROM_INTERNAL = 1;


    private String mClass;
    private String mDexPath;
    private Object mInstance;
    private Class<?> mLocalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDexPath = getIntent().getStringExtra(PluginCons.EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(PluginCons.EXTRA_CLASS);

        Log.d(TAG, "mClass=" + mClass + " mDexPath=" + mDexPath);
        if (mClass == null) {
            launchTargetActivity();
        } else {
            launchTargetActivity(mClass);
        }
    }

    @SuppressLint("NewApi")
    protected void launchTargetActivity() {
        PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(mDexPath, PackageManager.GET_ACTIVITIES);
        if ((packageInfo.activities != null)
                && (packageInfo.activities.length > 0)) {
            String activityName = packageInfo.activities[0].name;
            mClass = activityName;
            System.out.println("Class == " + mClass);
            launchTargetActivity(mClass);
        }
    }

    @SuppressLint("NewApi")
    protected void launchTargetActivity(final String className) {
        Log.d(TAG, "start launchTargetActivity, className=" + className);

        try {
            mLocalClass = PluginManager.getInstance().getClassLoader().loadClass(className);
            Constructor<?> localConstructor = mLocalClass
                    .getConstructor(new Class[] {});
            mInstance = localConstructor.newInstance(new Object[] {});
            Log.d(TAG, "instance = " + mInstance);

            Method setProxy = mLocalClass.getMethod("setProxy", new Class[] { Activity.class });
            setProxy.setAccessible(true);
            setProxy.invoke(mInstance, new Object[] { this });

            Method onCreate = mLocalClass.getDeclaredMethod("onCreate", new Class[] { Bundle.class });
            onCreate.setAccessible(true);
            Bundle bundle = new Bundle();
            bundle.putInt(FROM, FROM_EXTERNAL);
            onCreate.invoke(mInstance, new Object[] { bundle });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Method setProxy = null;
        try {
            setProxy = mLocalClass.getMethod("onResume", new Class[] {  });
            setProxy.setAccessible(true);
            setProxy.invoke(mInstance, new Object[] {  });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
