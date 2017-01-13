package com.whyalwaysmea.dragger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whyalwaysmea.dragger2.annotation.RedCloth;
import com.whyalwaysmea.dragger2.bean.Cloth;
import com.whyalwaysmea.dragger2.bean.Shoe;
import com.whyalwaysmea.dragger2.component.DaggerMainComponent;
import com.whyalwaysmea.dragger2.module.MainModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Shoe mShoe;

    @Inject
    @RedCloth
    Cloth mCloth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv = (TextView) findViewById(R.id.haha);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OldActivity.class);
                startActivity(intent);
            }
        });

        DaggerMainComponent
                .builder()
                .baseComponent(((MyApplication)getApplication()).getBaseComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
        tv.setText((mCloth == mShoe.getCloth()) + "");
    }

}
