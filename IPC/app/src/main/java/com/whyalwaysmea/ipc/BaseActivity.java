package com.whyalwaysmea.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Long
 * on 2016/11/11.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String contextString = this.toString();
        TAG  =  contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
        Log.e(TAG, Process.myPid() + "");
    }

    protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
