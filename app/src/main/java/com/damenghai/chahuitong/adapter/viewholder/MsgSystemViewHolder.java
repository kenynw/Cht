package com.damenghai.chahuitong.adapter.viewholder;

import android.support.annotation.LayoutRes;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Message;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MsgSystemViewHolder extends BaseViewHolder<Message> {

    @Bind(R.id.tv_system_title)
    TextView mTvTitle;

    @Bind(R.id.tv_system_time)
    TextView mTvTime;

    @Bind(R.id.tv_system_content)
    TextView mTvContent;

    public MsgSystemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_msg_system);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Message data) {
        if (!TextUtils.isEmpty(data.getMessage_title())) {
            mTvTitle.setText(data.getMessage_title());
            mTvTitle.setVisibility(View.VISIBLE);
        }
        mTvTime.setText(data.getMessage_time());
        mTvContent.setText(Html.fromHtml(data.getMessage_body()));
        mTvContent.setClickable(true);
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
