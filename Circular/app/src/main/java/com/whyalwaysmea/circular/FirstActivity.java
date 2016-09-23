package com.whyalwaysmea.circular;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        View v = findViewById(R.id.touch_me_view);
v.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent i = new Intent(FirstActivity.this, SecondActivity.class);
        i.putExtra("x", (int)event.getX());
        i.putExtra("y", (int)event.getY());
        startActivity(i);
        return false;
    }
});
    }
}
