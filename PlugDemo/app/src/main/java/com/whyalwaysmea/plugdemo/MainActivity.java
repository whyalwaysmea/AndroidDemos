package com.whyalwaysmea.plugdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whyalwaysmea.plug.IShowToast;

import java.lang.reflect.Method;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().install(this, PluginCons.DexPath);

        final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        final ImageView avator = (ImageView) findViewById(R.id.iv_avator);
        avator.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_avator));

        final TextView textView = (TextView) findViewById(R.id.test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int textColor = PluginManager.getInstance().getColor("textview_color");
                textView.setTextColor(textColor);

                int bgColor = PluginManager.getInstance().getColor("colorbg");
                textView.setTextColor(bgColor);

                Drawable drawable = PluginManager.getInstance().getPlugDrawable(MainActivity.this, R.drawable.icon_avator);
                avator.setBackgroundDrawable(drawable);

                View view = PluginManager.getInstance().getView(MainActivity.this);
                mainLayout.addView(view);
            }
        });

        avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐式启动
                Intent intent = new Intent("com.whyalwaysmea.plugin.proxy");
                intent.putExtra(PluginCons.EXTRA_DEX_PATH, PluginCons.DexPath);
                intent.putExtra(PluginCons.EXTRA_CLASS, "com.whyalwaysmea.plug.MainActivity");
                startActivity(intent);
            }
        });


        findViewById(R.id.hook_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Log.d(TAG, "TextView Class: "  + TextView.class.getClassLoader());
        Log.d(TAG, "classLoader : "  + getClassLoader());
        Log.d(TAG, "ClassLoader.getSystemClassLoader : "  + ClassLoader.getSystemClassLoader());
    }

    private void getCodeByInterface() {
        // 使用接口的方式
        try {
            Class clz = PluginManager.getInstance().getClassLoader().loadClass("com.whyalwaysmea.plug.ShowToastImpl");
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
            clazz = PluginManager.getInstance().getClassLoader().loadClass("com.whyalwaysmea.plug.ShowToastImpl");
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

}
