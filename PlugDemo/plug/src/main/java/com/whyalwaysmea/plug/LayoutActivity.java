package com.whyalwaysmea.plug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Toast.makeText(this, "I'm from plugin Activity", Toast.LENGTH_SHORT).show();
    }
}
