package com.example.why.skindemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.why.skindemo.R;

/**
 * Created by Long
 * on 2017/7/22.
 */

public class DayNightAdapter extends RecyclerView.Adapter<DayNightAdapter.SimpleViewHolder> {


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.item_day_night, parent, false);
        return new SimpleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
