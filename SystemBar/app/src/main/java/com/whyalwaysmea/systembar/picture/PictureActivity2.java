package com.whyalwaysmea.systembar.picture;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;
import com.whyalwaysmea.systembar.R;
import com.whyalwaysmea.systembar.utils.MeasureUtil;

public class PictureActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 设置全屏，并且不会Activity的布局让出状态栏的空间
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture2);
        StatusBarUtil.setTransparent(this);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        materialCollapsingForKitkat(toolbarLayout);
    }

    private void materialCollapsingForKitkat(final CollapsingToolbarLayout toolbarLayout) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

            // 设置Toolbar对顶部的距离
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolbar
                    .getLayoutParams();
            layoutParams.topMargin = MeasureUtil.getStatusBarHeight(this);
        }
    }
}
