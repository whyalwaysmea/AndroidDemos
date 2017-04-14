package com.whyalwaysmea.databindingsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.whyalwaysmea.databindingsample.bean.User;
import com.whyalwaysmea.databindingsample.databinding.SimpleBinding;

public class SimpleActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleBinding simpleBinding = DataBindingUtil.setContentView(this, R.layout.activity_simple);
        user = new User();
        simpleBinding.setUser(user);
        simpleBinding.setEvent(new Event());
        user.setFirstName("Hello");
        user.setLastName("World");
    }

    public class Event {
        public void onClick(View view) {
//            user.setLastName("DataBinding");
            Toast.makeText(SimpleActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
        }
    }
}
