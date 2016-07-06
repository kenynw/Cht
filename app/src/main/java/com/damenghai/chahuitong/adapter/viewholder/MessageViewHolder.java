package com.damenghai.chahuitong.adapter.viewholder;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Message;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MessageViewHolder extends BaseViewHolder<Message> {

    @Bind(R.id.dv_msg_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_msg_username)
    TextView mTvUsername;

    @Bind(R.id.tv_msg_content)
    TextView mTvContent;

    @Bind(R.id.tv_msg_time)
    TextView mTvTime;

    @Bind(R.id.dv_msg_trace_image)
    SimpleDraweeView mDvTraceImg;

    @Bind(R.id.tv_msg_trace_content)
    TextView mTvTrace;

    public MessageViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_message);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Message data) {
        mDvAvatar.setImageURI(Uri.parse(data.getFrom_member_avatar()));
        mTvUsername.setText(data.getFrom_member_name());
        mTvContent.setText(data.getMessage_body());
        mTvTime.setText(data.getMessage_time());
    }

}
