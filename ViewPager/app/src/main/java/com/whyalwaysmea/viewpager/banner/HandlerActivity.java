package com.whyalwaysmea.viewpager.banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.whyalwaysmea.viewpager.R;

public class HandlerActivity extends AppCompatActivity {

    private int mCurrentPage = 0;
    private int CHANGE_PAGE = 1;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyAdapter2(this));
        mCurrentPage = 5 * 10;
        mViewPager.setCurrentItem(mCurrentPage);
        mHandler.sendEmptyMessageDelayed(CHANGE_PAGE, 5000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPage++;
            mViewPager.setCurrentItem(mCurrentPage);
            mHandler.sendEmptyMessageDelayed(CHANGE_PAGE, 5000);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(CHANGE_PAGE);
        mHandler = null;
    }
}
