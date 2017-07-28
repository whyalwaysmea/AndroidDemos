package com.whyalwaysmea.plugdemo;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import com.whyalwaysmea.plugdemo.hook.PluginInstrumentation;
import com.whyalwaysmea.plugdemo.proxy.ProxyActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

import static com.whyalwaysmea.plugdemo.PluginCons.DRAWABLE;
import static com.whyalwaysmea.plugdemo.PluginCons.PLUG_NAME;

/**
 * Created by HanLong on 2017/7/26.
 */

public class PluginManager {
    private static final String OPT_DIR = "dex1";

    private static PluginManager instance;
    private static Resources mAppResources;
    private static ClassLoader mClassLoader;
    private static PackageInfo packageInfo;

    public static PluginManager getInstance(){
        if (instance == null) {
            instance = new PluginManager();
        }
        return instance;
    }

    public void install(Context context,String apkPath){
        getDexClassLoader(context, apkPath);
        getAppResources(context, apkPath);
        initPluginInfo(context, apkPath);
    }

    private void getDexClassLoader(Context context, String apkPath) {
        File optimizedDirectory = context.getDir(OPT_DIR, Context.MODE_PRIVATE);
        mClassLoader = new DexClassLoader(
                apkPath, optimizedDirectory.getAbsolutePath(),
                null,
                context.getClassLoader());
    }


    private void getAppResources(Context context,String apkPath){
        // 获取插件包中的资源
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Resources superRes = context.getResources();
        mAppResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());

    }

    private void  initPluginInfo(Context context,String apkPath){
        PackageManager pm = context.getPackageManager();
        packageInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
    }


    public int getColor(String colorName) {
        if (mAppResources == null) {
            throw new IllegalArgumentException("Pleas install plugin apk");
        }
        try {
            int colorId = mAppResources.getIdentifier(colorName, PluginCons.COLOR, PluginCons.PLUG_NAME);
            return mAppResources.getColor(colorId);

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Drawable getPlugDrawable(Context context, int drawableID) {
        if (mAppResources == null) {
            throw new IllegalArgumentException("Pleas install plugin apk");
        }
        try {
            // 返回的是资源文件名，如register_bg
            // String imgPath = getResources().getResourceEntryName(R.drawable.register_bg);

            String resName = context.getResources().getResourceEntryName(drawableID);

            // 获取资源id
            int drawableId = mAppResources.getIdentifier(resName, DRAWABLE, PLUG_NAME);
            return mAppResources.getDrawable(drawableId);

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public View getView(Context context) {
        // 加载View
        XmlResourceParser layoutParser = mAppResources.getLayout(mAppResources.getIdentifier("activity_layout", "layout", PLUG_NAME));
        View bundleView  = LayoutInflater.from(context).inflate(layoutParser, null);
        return bundleView;
    }

    public void startPlugin(Context context){
        String className = getPluginMainActivity();
        Intent intent = new Intent(context,ProxyActivity.class);
        intent.putExtra("class",className);
        context.startActivity(intent);
    }

    public void a() {
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            // 拿到原始的 mInstrumentation字段
            Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

            //如果没有注入过，就执行替换
            if (!(mInstrumentation instanceof PluginInstrumentation)) {
                PluginInstrumentation pluginInstrumentation = new PluginInstrumentation(mInstrumentation);
                mInstrumentationField.set(currentActivityThread, pluginInstrumentation);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    private String getPluginMainActivity() {
        return packageInfo.activities[0].name;
    }

    public ClassLoader getClassLoader() {
        return mClassLoader;
    }

    public Resources getResource() {
        return mAppResources;
    }
}
