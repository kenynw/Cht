package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.module.user.UserDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserViewHolder extends BaseViewHolder<User> {

    @Bind(R.id.item_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.item_user_name)
    TextView mTvName;

    public UserViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_galley_user_commend);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(User data) {
        mDvAvatar.setImageURI(Uri.parse(data.getMember_avatar()));
        mTvName.setText(data.getMember_name());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserDetailActivity.class);
            i.putExtra("user_id", data.getMember_id());
            getContext().startActivity(i);
        });
    }

    public void setMore() {
        mTvName.setText(R.string.btn_view_more);
        itemView.setOnClickListener(v -> getContext().startActivity(new Intent(getContext(), UserDetailActivity.class)));
    }

}
