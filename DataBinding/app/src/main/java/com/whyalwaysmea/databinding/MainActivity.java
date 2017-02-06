package com.whyalwaysmea.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.whyalwaysmea.databinding.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private User mUser = new User("Why", "alwayse me a ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setUser(mUser);
        mainBinding.setEvent(new Event());
        mainBinding.viewStub.getViewStub().inflate();
    }

    public class Event {
        public void clickFisrName(View view) {
            Toast.makeText(MainActivity.this, "onclick", Toast.LENGTH_SHORT).show();
            mUser.setFirstName("aaaaaaaaaaaa");
            mUser.setLastName("bbbbbbbb");
        }

        public void clickLastName(User user) {
            Toast.makeText(MainActivity.this, "lastName" + user.getLastName(), Toast.LENGTH_SHORT).show();
        }
    }
}
