package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Message;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MsgFansViewHolder extends BaseViewHolder<Message> {

    @Bind(R.id.dv_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_user_username)
    TextView mTvUsername;

    @Bind(R.id.tv_user_time)
    TextView mTvTime;

    public MsgFansViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_fans);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Message data) {
        mDvAvatar.setImageURI(Uri.parse(data.getFrom_member_avatar()));
        mTvUsername.setText(data.getFrom_member_name());
        mTvTime.setText(data.getMessage_time());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserInfoActivity.class);
            i.putExtra("user_id", data.getFrom_member_id());
            getContext().startActivity(i);
        });
    }
}
