package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.UserCommendViewHolder;
import com.damenghai.chahuitong.model.bean.User;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserGridAdapter extends BaseRecycleListAdapter<User, UserCommendViewHolder> {
    public UserGridAdapter(Context context, List<User> data) {
        super(context, data);
    }

    @Override
    public UserCommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCommendViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(UserCommendViewHolder holder, int position) {
        if (position == mData.size()) {
            holder.setMore();
        } else {
            holder.setData(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }
}
