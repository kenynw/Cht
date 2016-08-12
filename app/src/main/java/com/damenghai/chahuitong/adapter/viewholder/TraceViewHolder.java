package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceViewHolder extends TraceCommonViewHolder {

    @Bind(R.id.dv_trace_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_trace_username)
    TextView mTvUsername;

    public TraceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_trace);
    }

    @Override
    public void setData(Trace trace) {
        super.setData(trace);
        mDvAvatar.setImageURI(Uri.parse(trace.getTrace_memberavatar()));
        mTvUsername.setText(trace.getTrace_membername());
        mDvAvatar.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserInfoActivity.class);
            i.putExtra("user_id", trace.getTrace_memberid());
            getContext().startActivity(i);
        });
    }

}
