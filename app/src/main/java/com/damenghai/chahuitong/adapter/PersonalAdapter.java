package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Personal;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.personal.FavoritesActivity;
import com.damenghai.chahuitong.module.personal.VoucherActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
