package com.damenghai.chahuitong.adapter.viewholder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceCommentViewHolder extends BaseViewHolder<TraceComment> {

    @Bind(R.id.dv_trace_comment_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_trace_comment_user)
    TextView mTvUsername;

    @Bind(R.id.tv_trace_comment_time)
    TextView mTvTime;

    @Bind(R.id.tv_trace_comment_like)
    TextView mTvLike;

    @Bind(R.id.tv_trace_comment_content)
    TextView mTvContent;

    public TraceCommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_trace_comment);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TraceComment comment) {
        mDvAvatar.setImageURI(Uri.parse(comment.getComment_memberavatar()));
        mTvUsername.setText(comment.getComment_membername());
        mTvTime.setText(comment.getComment_addtime());
        mTvLike.setOnClickListener(v -> TraceModel.getInstance().addCommentLike(comment.getComment_id()).subscribe(new ServiceResponse<>()));
        mTvContent.setText(comment.getComment_content());
    }
}
