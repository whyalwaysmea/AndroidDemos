package com.whyalwaysmea.systembar.color;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.whyalwaysmea.systembar.R;

/**
 * 不使用第三方工具类
 */
public class ColorActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        // 必须的设置，不然会混淆状态栏
        setSupportActionBar(toolbar);

        // 设置返回按钮
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
