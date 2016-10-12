package com.whyalwaysmea.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whyalwaysmea.viewpager.banner.BannerActivity;
import com.whyalwaysmea.viewpager.lazyload.LazyLoadActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button banner = (Button) findViewById(R.id.button);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BannerActivity.class);
                startActivity(intent);
            }
        });

        Button lazyLoad = (Button) findViewById(R.id.button2);
        lazyLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LazyLoadActivity.class);
                startActivity(intent);
            }
        });
    }
}
