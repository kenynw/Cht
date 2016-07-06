package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceViewHolder extends BaseViewHolder<Trace> {

    @Bind(R.id.dv_trace_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_trace_username)
    TextView mTvUsername;

    @Bind(R.id.tv_trace_time)
    TextView mTvTime;

    @Bind(R.id.tv_trace_content)
    TextView mTvContent;

    @Bind(R.id.dv_trace_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.btn_trace_share)
    Button mBtnShare;

    @Bind(R.id.btn_trace_comment)
    Button mBtnComment;

    @Bind(R.id.btn_trace_like)
    Button mBtnLike;

    public TraceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_trace);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Trace trace) {
        mDvAvatar.setImageURI(Uri.parse(trace.getTrace_memberavatar()));
        mTvUsername.setText(trace.getTrace_membername());
        mTvTime.setText(trace.getTrace_addtime());
        mTvContent.setText(trace.getTrace_title());
        mDvImage.setImageURI(Uri.parse(trace.getTrace_image()));
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), TraceDetailActivity.class);
            i.putExtra("trace_id", trace.getTrace_id());
            getContext().startActivity(i);
        });
        mDvAvatar.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserInfoActivity.class);
            i.putExtra("user_id", trace.getTrace_memberid());
            getContext().startActivity(i);
        });
        mBtnLike.setOnClickListener(v -> TraceModel.getInstance().addTraceLike(trace.getTrace_id()).subscribe(new ServiceResponse<>()));
        mBtnLike.setText(String.format(getContext().getString(R.string.btn_trace_like), trace.getTrace_likecount() > 0 ? trace.getTrace_likecount() : ""));
        mBtnComment.setText(String.format(getContext().getString(R.string.btn_trace_comment), trace.getTrace_commentcount() > 0 ? trace.getTrace_commentcount() : ""));
    }
}
