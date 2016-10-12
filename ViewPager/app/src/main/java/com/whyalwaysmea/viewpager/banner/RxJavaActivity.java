package com.whyalwaysmea.viewpager.banner;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.whyalwaysmea.viewpager.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxJavaActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewpager;
    private int mCurrentPage;
    private int[] imgIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};
    private int[] imgs = {R.drawable.a5, R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

        mCurrentPage = 1;
        mViewpager.setAdapter(new MyAdapter(this,imgs));
        mViewpager.setCurrentItem(mCurrentPage);

        Observable.interval(5, 5, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        // 进行轮播操作
                        mCurrentPage++;
                        mViewpager.setCurrentItem(mCurrentPage);
                    }
                });

        mViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            // 闲置中
            case ViewPager.SCROLL_STATE_IDLE:
                // “偷梁换柱”
                if (mViewpager.getCurrentItem() == 0) {
                    mViewpager.setCurrentItem(imgIds.length, false);
                } else if (mViewpager.getCurrentItem() == imgIds.length + 1) {
                    mViewpager.setCurrentItem(1, false);
                }
                mCurrentPage = mViewpager.getCurrentItem();
                break;
        }
    }
}
