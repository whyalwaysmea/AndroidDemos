package com.whyalwaysmea.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Long
 * on 2016/9/21.
 */

public class MyAdapter2 extends PagerAdapter{

    private int[] imgId = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};
    private Context mContext;

    public MyAdapter2(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setBackgroundResource(imgId[position % imgId.length]);
        container.addView(view);
        return view;
    }
}
