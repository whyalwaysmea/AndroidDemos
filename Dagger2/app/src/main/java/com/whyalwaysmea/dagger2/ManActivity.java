package com.whyalwaysmea.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whyalwaysmea.dagger2.bean.Car;
import com.whyalwaysmea.dagger2.component.DaggerManComponent;

import javax.inject.Inject;
import javax.inject.Named;

public class ManActivity extends AppCompatActivity {

    @Inject
    @Named("red")
    Car redCar;

//    @Inject
//    @Named("blue")
//    Car bludCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerManComponent.builder().build().inject(this);

        Log.d("Car", redCar.toString() + "--" + redCar.getColor());
//        Log.d("Car", bludCar.toString() + "--" + bludCar.getColor());
    }
}
