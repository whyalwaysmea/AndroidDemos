package com.whyalwaysmea.apt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.DIActivity;
import com.example.DIView;
import com.example.Test;

@Test(2)
@DIActivity
public class MainActivity extends AppCompatActivity {

    @DIView(R.id.hello)
    TextView helloDi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DIMainActivity.bindView(this);

        helloDi.setText("hello APT !!!");
    }
}
