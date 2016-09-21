package com.whyalwaysmea.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity {

    private int mCurrentPage = 0;
    private int CHANGE_PAGE = 1;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyAdapter(this));
        mHandler.sendEmptyMessageDelayed(CHANGE_PAGE, 5000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPage++;
            if(mCurrentPage == 6) {
                mCurrentPage = 0;
            }
            mViewPager.setCurrentItem(mCurrentPage);
            mHandler.sendEmptyMessageDelayed(CHANGE_PAGE, 5000);
        }
    };
}
