package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Personal;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PersonalViewHolder extends BaseViewHolder<Personal> {

    @Bind(R.id.iv_personal_icon)
    ImageView mIcon;

    @Bind(R.id.tv_personal_text)
    TextView mText;

    public PersonalViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_grid_personal);
        itemView.setMinimumHeight(parent.getHeight() / 2 - 2);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Personal data) {
        mIcon.setImageResource(data.getIconRes());
        mText.setText(data.getName());
        itemView.setOnClickListener(v -> {
            if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(i);
            } else {
                Intent intent = new Intent(data.getAction());
                getContext().startActivity(intent);
            }
        });
    }
}
