package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.ConsultViewHolder;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaConsultAdapter extends BaseRecycleListAdapter<Consult, ConsultViewHolder> {

    public FleaConsultAdapter(Context context, List<Consult> data) {
        super(context, data);
    }

    @Override
    public ConsultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConsultViewHolder(parent, R.layout.item_list_flea_consult);
    }

    @Override
    public void onBindViewHolder(ConsultViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
