package com.whyalwaysmea.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.whyalwaysmea.databinding.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            users.add(new User("haha", "hehe"));
        }

        UserAdapter userAdapter = new UserAdapter(this);
        userAdapter.setUserList(users);
        binding.recyclerview.setAdapter(userAdapter);
    }
}
