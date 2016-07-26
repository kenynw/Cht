package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Personal;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PersonalAdapter extends RecyclerView.Adapter<PersonalViewHolder> {

    private final int[] ICON_RES_LIST = new int[]{
            R.mipmap.ic_personal_favorites, R.mipmap.ic_personal_voucher,
            R.mipmap.ic_personal_voucher, R.mipmap.ic_personal_voucher,
            R.mipmap.ic_personal_active, R.mipmap.ic_personal_feedback
    };

    private Context mContext;

    private List<Personal> mData;

    public PersonalAdapter(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        String[] names = mContext.getResources().getStringArray(R.array.personal_name_list);
        String[] actions = mContext.getResources().getStringArray(R.array.personal_action_list);
        for (int i=0; i<names.length; i++) {
            Personal personal = new Personal();
            personal.setName(names[i]);
            personal.setIconRes(ICON_RES_LIST[i]);
            personal.setAction(actions[i]);
            mData.add(personal);
        }
    }

    @Override
    public void onBindViewHolder(PersonalViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public PersonalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalViewHolder(parent);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
