package com.damenghai.chahuitong.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Personal;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.personal.FavoritesActivity;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.module.personal.VoucherActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

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
            Intent intent = new Intent(data.getAction());
            getContext().startActivity(intent);
        });
    }
}
