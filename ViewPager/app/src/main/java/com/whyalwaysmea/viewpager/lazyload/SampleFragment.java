package com.whyalwaysmea.viewpager.lazyload;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Long
 * on 2016/10/12.
 */

public class SampleFragment extends BaseFragment {

    private TextView label;
    private int page = -1;
    private int param = -1;

    public static SampleFragment newInstance(int page) {
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        page = getArguments().getInt("page");
        Log.e("", toString() + ":onAttach");
        enableLazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        label = new TextView(getContext());
        label.setText("page:" + page);
        return label;
    }

    @Override
    public void setUpView(View view) {

    }

    @Override
    public void setUpData() {

        if (param == -1) {
            param = new Random().nextInt(10);
        }
        label.append("load data:" + param);

//        load data
//        bind data
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("param", param);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            param = savedInstanceState.getInt("param");
        }
    }

    @Override
    public String toString() {
        return "-------page : " + page;
    }

}
