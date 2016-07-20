package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.ImageBrowseActivity;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceHomeViewHolder extends TraceCommonViewHolder{

    @Bind(R.id.tv_trace_more)
    TextView mTvMore;

    public TraceHomeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_user_trace);
    }

    @Override
    public void setData(Trace trace) {
        super.setData(trace);
        mTvMore.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setItems(new String[]{"删除", "举报"}, (dialog, which) -> {
                    switch (which) {
                        case 0 :
                            LUtils.toast("删除动态");
                            break;
                        case 1 :
                            LUtils.toast("举报动态");
                            break;
                    }
                }).show());
    }
}
