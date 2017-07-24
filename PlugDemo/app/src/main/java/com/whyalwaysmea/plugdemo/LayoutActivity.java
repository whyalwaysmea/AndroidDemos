package com.whyalwaysmea.plugdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.reflect.Field;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context context) {
        replaceContextResources(context);
        super.attachBaseContext(context);
    }

    /**
     * 使用反射的方式，使用Bundle的Resource对象，替换Context的mResources对象
     * @param context
     */
    public void replaceContextResources(Context context){
        try {
            Field field = context.getClass().getDeclaredField("mResources");
            field.setAccessible(true);
            field.set(context, ResourcesManager.resources);
            System.out.println("debug:repalceResources succ");
        } catch (Exception e) {
            System.out.println("debug:repalceResources error");
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        TextView textView = (TextView) findViewById(R.id.get_layout);

    }
}
