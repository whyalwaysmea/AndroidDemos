package com.whyalwaysmea.plugdemo;

import android.Manifest;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whyalwaysmea.plug.IShowToast;
import com.whyalwaysmea.plugdemo.hook.HookInstrumentation;
import com.whyalwaysmea.plugdemo.proxy.ProxyActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private HookInstrumentation mHookInstrumentation;
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachContext() throws Exception {
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        // 创建代理对象
        mHookInstrumentation = new HookInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, mHookInstrumentation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            PluginManager.getInstance().install(this, PluginCons.DexPath);
        }

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
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                intent.putExtra(PluginCons.EXTRA_DEX_PATH, PluginCons.DexPath);
                intent.putExtra(PluginCons.EXTRA_CLASS, "com.whyalwaysmea.plug.MainActivity");
                startActivity(intent);
            }
        });


        findViewById(R.id.hook_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHookInstrumentation.setContext(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra(PluginCons.FLAG_ACTIVITY_CLASS_NAME, "com.whyalwaysmea.plug.TestActivity");
                startActivity(intent);
            }
        });

        Log.d(TAG, "TextView Class: " + TextView.class.getClassLoader());
        Log.d(TAG, "classLoader : " + getClassLoader());
        Log.d(TAG, "ClassLoader.getSystemClassLoader : " + ClassLoader.getSystemClassLoader());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PluginManager.getInstance().install(this, PluginCons.DexPath);
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
