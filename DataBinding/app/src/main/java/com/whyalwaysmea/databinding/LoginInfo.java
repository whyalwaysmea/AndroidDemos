package com.whyalwaysmea.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by HanLong on 2017/4/14.
 */

public class LoginInfo extends BaseObservable{
    public String phone;

    public LoginInfo(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
}
