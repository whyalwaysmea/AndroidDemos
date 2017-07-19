package com.whyalwaysmea.plugdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dexOutputDir = getDir("dex1", 0);
        String dexPath = Environment.getExternalStorageDirectory().toString() + "/wtp/" + "output.jar";
        DexClassLoader loader = new DexClassLoader(dexPath,
                dexOutputDir.getAbsolutePath(),
                null, getClassLoader());

        // 使用接口的方式
        /*try {
            Class clz = loader.loadClass("com.whyalwaysmea.plug.ShowToastImpl");
            IShowToast impl = (IShowToast) clz.newInstance();
            impl.showToast(this);
        } catch (Exception e) {
            Log.d("TEST111", "error happened", e);
        }*/


        // 反射
        Class clazz  = null;
        try{
            clazz  = loader.loadClass("com.whyalwaysmea.plug.ShowToastImpl");
            // 遍历类里所有方法
            Method[]methods = clazz .getDeclaredMethods();
            for(int i = 0; i < methods.length; i++){
                Log.e(TAG,methods[i].toString());
            }
            Method method= clazz.getDeclaredMethod("showToast", Context.class);// 获取方法
            method.setAccessible(true);// 把方法设为public，让外部可以调用
            int returnValue = (int) method.invoke(clazz.newInstance(), MainActivity.this);// 调用方法并获取返回值
            Log.e(TAG, returnValue + "");

        }catch(Exception exception){
            // Handle exception gracefully here.
            Log.e(TAG, "Exception == " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
