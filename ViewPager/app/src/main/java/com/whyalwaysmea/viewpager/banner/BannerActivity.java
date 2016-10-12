package com.whyalwaysmea.viewpager.banner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whyalwaysmea.viewpager.R;

public class BannerActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button handlerBtn = (Button) findViewById(R.id.handler);
		Button rxJavaBtn = (Button) findViewById(R.id.rxjava);

		handlerBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(BannerActivity.this, HandlerActivity.class);
				startActivity(intent);
			}
		});
		rxJavaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(BannerActivity.this, RxJavaActivity.class);
				startActivity(intent);
			}
		});
	}


}
