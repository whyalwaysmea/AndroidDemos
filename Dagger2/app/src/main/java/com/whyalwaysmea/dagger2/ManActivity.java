package com.whyalwaysmea.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whyalwaysmea.dagger2.bean.Car;
import com.whyalwaysmea.dagger2.component.DaggerManComponent;

import javax.inject.Inject;

public class ManActivity extends AppCompatActivity {

    @Inject
    Car mCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerManComponent.create().inject(this);
        Log.d("Car", mCar.toString());

        DaggerManComponent.builder().build().inject(this);
        Log.d("Car", mCar.toString());
    }
}
