package com.whyalwaysmea.stickview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class CActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        System.out.println("onCreate");
        RelativeLayout c = (RelativeLayout) findViewById(R.id.activity_c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CActivity.this, DActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");

    }
}
