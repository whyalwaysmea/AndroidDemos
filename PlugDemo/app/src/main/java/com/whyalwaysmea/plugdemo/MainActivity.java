package com.whyalwaysmea.plugdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whyalwaysmea.plug.IShowToast;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PLUG_NAME = "com.whyalwaysmea.plug";
    private static final String COLOR = "color";
    private static final String DRAWABLE = "drawable";
    private DexClassLoader mLoader;
    private String mDexPath;
    private Resources mPlugResources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        File dexOutputDir = getDir("dex1", 0);
        mDexPath = Environment.getExternalStorageDirectory().toString() + "/wtp/" + "plug.apk";
        mLoader = new DexClassLoader(mDexPath, dexOutputDir.getAbsolutePath(), null, getClassLoader());

        final ImageView avator = (ImageView) findViewById(R.id.iv_avator);
        avator.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_avator));

        final TextView textView = (TextView) findViewById(R.id.test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getCodeByInterface();
                int textviewColor = getColor("textview_color");
                textView.setTextColor(textviewColor);

                int colorbg = getColor("colorbg");
                textView.setBackgroundColor(colorbg);

                Drawable plugDrawable = getPlugDrawable(R.drawable.icon_avator);
                avator.setBackgroundDrawable(plugDrawable);

                View view = getView();
                mainLayout.addView(view);
            }
        });

        avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LayoutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCodeByInterface() {
        // 使用接口的方式
        try {
            Class clz = mLoader.loadClass("com.whyalwaysmea.plug.ShowToastImpl");
            IShowToast impl = (IShowToast) clz.newInstance();
            impl.showToast(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCodeByReflect() {
        // 反射
        Class clazz = null;
        try {
            clazz = mLoader.loadClass("com.whyalwaysmea.plug.ShowToastImpl");
            // 遍历类里所有方法
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Log.e(TAG, methods[i].toString());
            }
            Method method = clazz.getDeclaredMethod("showToast", Context.class);// 获取方法
            method.setAccessible(true);// 把方法设为public，让外部可以调用
            int returnValue = (int) method.invoke(clazz.newInstance(), MainActivity.this);// 调用方法并获取返回值
            Log.e(TAG, returnValue + "");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Resources getPlugResources() {
        // 获取插件包中的资源
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, mDexPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (assetManager == null) {
            return null;
        }
        Resources superRes = super.getResources();
        mPlugResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
        ResourcesManager.resources = mPlugResources;
        return mPlugResources;
    }

    public int getColor(String colorName) {
        if (mPlugResources == null) {
            getPlugResources();
        }
        try {
            return mPlugResources.getColor(mPlugResources.getIdentifier(colorName, COLOR, PLUG_NAME));

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Drawable getPlugDrawable(int drawableID) {
        if (mPlugResources == null) {
            getPlugResources();
        }
        try {
            // 返回的是资源文件名，如register_bg
            // String imgPath = getResources().getResourceEntryName(R.drawable.register_bg);
            String resName = getResources().getResourceEntryName(drawableID);


            // 获取资源id
            int drawableId = mPlugResources.getIdentifier(resName, DRAWABLE, PLUG_NAME);
            return mPlugResources.getDrawable(drawableId);

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public View getView() {
        // 加载View
        XmlResourceParser layoutParser = mPlugResources.getLayout(mPlugResources.getIdentifier("activity_main", "layout", PLUG_NAME));
        View bundleView  = LayoutInflater.from(this).inflate(layoutParser, null);
        return bundleView;
    }

}
