package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.CateViewHolder;
import com.damenghai.chahuitong.model.bean.Category;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CateImageAdapter extends RecyclerArrayAdapter<Category> {

    public CateImageAdapter(Context context, List<Category> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CateViewHolder(parent);
    }
}
