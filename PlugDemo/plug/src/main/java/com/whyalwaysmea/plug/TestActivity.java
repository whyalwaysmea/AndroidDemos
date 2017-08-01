package com.whyalwaysmea.plug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;


public class TestActivity extends BaseActivity{

    private Button mButton;
    public static final String FLAG_ACTIVITY_CLASS_NAME = "FLAG_ACTIVITY_CLASS_NAME";


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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PROXY_VIEW_ACTION);
                intent.putExtra(FLAG_ACTIVITY_CLASS_NAME, "com.whyalwaysmea.plug.MainActivity");
                startActivity(intent);
            }
        });
    }
}
