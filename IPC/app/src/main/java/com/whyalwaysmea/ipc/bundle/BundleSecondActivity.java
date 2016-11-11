package com.whyalwaysmea.ipc.bundle;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.whyalwaysmea.ipc.BaseActivity;
import com.whyalwaysmea.ipc.R;
import com.whyalwaysmea.ipc.model.User;

public class BundleSecondActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_second);

        Log.e("BundleSecondActivity", Process.myPid() + "");

        User user = (User) getIntent().getSerializableExtra("User");
        Log.e("BundleSecondActivity", user.getName() + " .. " + user.getAge());
    }
}
