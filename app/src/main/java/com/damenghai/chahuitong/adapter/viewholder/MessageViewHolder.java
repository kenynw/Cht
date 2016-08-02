package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Message;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
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

    @Bind(R.id.ly_msg_trace)
    LinearLayout mLyTrace;

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
        mDvAvatar.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserInfoActivity.class);
            i.putExtra("user_id", data.getFrom_member_id());
            getContext().startActivity(i);
        });
        mTvUsername.setText(data.getFrom_member_name());
        mTvContent.setText(Html.fromHtml(data.getMessage_body()));
        mTvContent.setClickable(true);
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
        mTvTime.setText(data.getMessage_time());
        if (data.getTrace_info() != null) {
            mLyTrace.setOnClickListener(v -> {
                Intent i = new Intent(getContext(), TraceDetailActivity.class);
                i.putExtra("trace_id", data.getTrace_info().getTrace_id());
                getContext().startActivity(i);
            });
            mDvTraceImg.setImageURI(Uri.parse(data.getTrace_info().getTrace_image()));
            mTvTrace.setText(Html.fromHtml(data.getTrace_info().getTrace_title()));
            mTvTrace.setClickable(true);
            mTvTrace.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

}
