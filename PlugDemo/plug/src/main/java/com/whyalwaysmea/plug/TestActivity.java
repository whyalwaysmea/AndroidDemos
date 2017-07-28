package com.whyalwaysmea.plug;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;


public class TestActivity extends BaseActivity{

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton = new Button(mProxyActivity);
        mButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        mButton.setBackgroundColor(Color.YELLOW);
        mButton.setText("这是测试页面");
        setContentView(mButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mButton.setText("这是测试页面 \r\n 我是onResume()");
    }
}
