package com.whyalwaysmea.databinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by HelloWorld on 2017/2/4.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private T binding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
