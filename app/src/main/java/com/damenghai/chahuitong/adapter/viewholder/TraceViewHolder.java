package com.damenghai.chahuitong.adapter.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.ImageBrowseActivity;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.ButterKnife;

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
