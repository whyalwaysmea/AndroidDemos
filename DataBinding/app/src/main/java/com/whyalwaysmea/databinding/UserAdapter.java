package com.whyalwaysmea.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HelloWorld on 2017/2/4.
 */

public class UserAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private final LayoutInflater mInflater;
    private List<User> mUserList;

    public UserAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mUserList = new ArrayList<>();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ViewDataBinding bind = DataBindingUtil.bind(view);
        return new BindingViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.getBinding().setVariable(BR.item, user);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void setUserList(List<User> userList) {
        mUserList = userList;
    }
}
