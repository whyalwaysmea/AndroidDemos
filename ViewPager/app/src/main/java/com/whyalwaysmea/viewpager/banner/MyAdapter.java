package com.whyalwaysmea.viewpager.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whyalwaysmea.viewpager.R;

/**
 * Created by Long
 * on 2016/9/21.
 */

public class MyAdapter extends PagerAdapter{

    private int[] imgId;
    private Context mContext;

    public MyAdapter(Context context, int[] imgs) {
        this.mContext = context;
        this.imgId = imgs;
    }

    @Override
    public int getCount() {
        return imgId.length;
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
        imageView.setBackgroundResource(imgId[position]);
        container.addView(view);
        return view;
    }
}
