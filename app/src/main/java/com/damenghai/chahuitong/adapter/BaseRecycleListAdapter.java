package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.damenghai.chahuitong.utils.T;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseRecycleListAdapter<BeanType, VHType extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VHType> {

    protected Context mContext;

    protected List<BeanType> mData;

    public BaseRecycleListAdapter(Context context, List<BeanType> data) {
        mContext = context;
        mData = data;
    }

    public void addList(List<BeanType> list) {
        if (list != null && list.size() > 0) {
            for (BeanType bean : list) {
                if (!mData.contains(bean)) mData.add(bean);
            }
            notifyDataSetChanged();
        }
    }

    public List<BeanType> getList() {
        return mData;
    }

    public void add(BeanType bean) {
        if (bean != null && mData != null && !mData.contains(bean)) {
            mData.add(bean);
            notifyItemInserted(mData.size() - 1);
        }
    }

    public void add(int position, BeanType bean) {
        if (bean != null && mData != null && !mData.contains(bean)) {
            mData.add(position, bean);
            notifyItemInserted(position);
        }
    }

}
