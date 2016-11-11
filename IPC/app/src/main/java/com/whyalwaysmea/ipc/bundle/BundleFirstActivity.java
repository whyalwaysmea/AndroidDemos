package com.whyalwaysmea.ipc.bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.whyalwaysmea.ipc.BaseActivity;
import com.whyalwaysmea.ipc.R;
import com.whyalwaysmea.ipc.model.User;

public class BundleFirstActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_first);


        TextView sendByBundle = (TextView) findViewById(R.id.sendByBundle);
        sendByBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BundleFirstActivity.this, BundleSecondActivity.class);
                intent.putExtra("User", new User("haha", 222));
                startActivity(intent);
            }
        });
    }
}
