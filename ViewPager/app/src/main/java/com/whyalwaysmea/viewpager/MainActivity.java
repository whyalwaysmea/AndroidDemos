package com.whyalwaysmea.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button handlerBtn = (Button) findViewById(R.id.handler);
		Button rxJavaBtn = (Button) findViewById(R.id.rxjava);

		handlerBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
				startActivity(intent);
			}
		});
		rxJavaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
				startActivity(intent);
			}
		});
	}


}
