package com.whyalwaysmea.ipc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.whyalwaysmea.ipc.bundle.BundleFirstActivity;
import com.whyalwaysmea.ipc.file.FileFirstActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bundle = (Button) findViewById(R.id.bundle);
        bundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(BundleFirstActivity.class);
            }
        });

        Button file = (Button) findViewById(R.id.file);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(FileFirstActivity.class);
            }
        });
    }

}
