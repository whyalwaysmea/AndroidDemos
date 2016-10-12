package com.whyalwaysmea.viewpager.lazyload;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Long
 * on 2016/10/12.
 */

public abstract class BaseFragment extends Fragment {

    private boolean isVisibleToUser;
    private boolean isViewInitialized;
    private boolean isDataInitialized;
    private boolean isLazyLoadEnabled;

    public abstract void setUpView(View view);
    public abstract void setUpData();

    public void enableLazyLoad(){
        isLazyLoadEnabled = true;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseFragment", toString() + ":onCreate");
    }

@Override
public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    this.isVisibleToUser = isVisibleToUser;
    Log.e("BaseFragment", toString() + ":setUserVisibleHint:" + isVisibleToUser);
    checkIfLoadData();
}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("BaseFragment", toString() + ":onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("BaseFragment", toString() + ":onViewCreated");
        if (!isLazyLoadEnabled){
            setUpView(view);
            setUpData();
        }else {
            setUpView(view);
            isViewInitialized = true;
            if (savedInstanceState != null){
                onRestoreInstanceState(savedInstanceState);
            }
            if (isDataInitialized){
                setUpData();
            }else {
                checkIfLoadData();
            }
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInitialized = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("BaseFragment", toString() + ":onActivityCreated");

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("BaseFragment", toString() + ":onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized = false;
        Log.e("BaseFragment", toString() + ":onDestroyView");
    }

    private void checkIfLoadData() {
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            isDataInitialized = true;
//            TODO load data
            setUpData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("BaseFragment", toString() + ":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("BaseFragment", toString() + ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("BaseFragment", toString() + ":onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("BaseFragment", toString() + ":onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("BaseFragment", toString() + ":onStop");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BaseFragment", toString() + ":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("BaseFragment", toString() + ":onDetach");
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.e("BaseFragment", toString() + ":onInflate");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("BaseFragment", toString() + ":onHiddenChanged:" + hidden);
    }
}
