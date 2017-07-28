package com.whyalwaysmea.plug;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends BaseActivity {

    private static final String TAG = "Client-MainActivity";
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        mProxyActivity.setContentView(generateContentView(mProxyActivity));
    }

    private View generateContentView(final Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.parseColor("#F79AB5"));
        mButton = new Button(context);
        mButton.setText("button");
        layout.addView(mButton, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByProxy("com.whyalwaysmea.plug.TestActivity");
            }
        });
        return layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Plugin");
        mButton.setBackgroundColor(Color.parseColor("#556677"));
    }

    protected void changeButton() {
        mButton.setBackgroundColor(Color.parseColor("#F79AB5"));
    }
}
