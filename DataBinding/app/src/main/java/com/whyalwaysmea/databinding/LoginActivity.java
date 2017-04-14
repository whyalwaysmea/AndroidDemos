package com.whyalwaysmea.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.whyalwaysmea.databinding.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        final LoginInfo loginInfo = new LoginInfo("Hello world");
        loginBinding.setLogin(loginInfo);
        loginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginInfo.setPhone("Ha ha ha ha ~");
                System.out.println(loginInfo.getPhone());
            }
        });

    }
}
