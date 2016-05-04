package com.damenghai.chahuitong.adapter;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Comment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CommentViewHolder extends BaseViewHolder<Comment> {

    @Bind(R.id.dv_comment_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_comment_name)
    TextView mTvName;

    @Bind(R.id.tv_comment_date)
    TextView mTvTime;

    @Bind(R.id.tv_comment_content)
    TextView mTvContent;

    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_comment);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Comment comment) {
        mDvAvatar.setImageURI(Uri.parse(comment.getGeval_frommemberavatar()));
        mTvName.setText(comment.getGeval_frommembername());
        mTvTime.setText(comment.getAdd_time_text());
        mTvContent.setText(comment.getGeval_content());
    }
}
