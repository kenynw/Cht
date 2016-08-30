package com.damenghai.chahuitong.adapter.viewholder;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
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

    @Bind(R.id.iv_trace_comment_like)
    ImageView mIvLike;

    @Bind(R.id.tv_trace_comment_like)
    TextView mTvLike;

    @Bind(R.id.tv_trace_comment_content)
    TextView mTvContent;

    @Bind(R.id.tv_comment_original)
    TextView mTvOriginal;

    public TraceCommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_trace_comment);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TraceComment comment) {
        mDvAvatar.setImageURI(Uri.parse(comment.getComment_memberavatar()));
        mDvAvatar.setOnClickListener(view -> toUserInfo(comment.getComment_memberid()));
        mTvUsername.setText(comment.getComment_membername());
        mTvUsername.setOnClickListener(view -> toUserInfo(comment.getComment_memberid()));

        mTvTime.setText(comment.getComment_addtime());
        mTvContent.setText(comment.getComment_content());

        mTvLike.setText(String.valueOf(comment.getLike_count()));
        if (comment.is_like()) {
            mIvLike.setImageResource(R.mipmap.ic_like_checked);
        } else {
            mIvLike.setImageResource(R.mipmap.ic_like_normal);
            mIvLike.setOnClickListener(v -> addLike(comment.getComment_id()));
            mTvLike.setOnClickListener(v -> addLike(comment.getComment_id()));
        }

        if (comment.getComment_reply() != null) {
            mTvOriginal.setVisibility(View.VISIBLE);
            mTvOriginal.setText(String.format(getContext().getString(R.string.text_original_comment), comment.getComment_reply().getComment_membername(), comment.getComment_reply().getComment_content()));
        }
    }

    private void addLike(int id) {
        TraceModel.getInstance().addCommentLike(id)
                .subscribe(new ServiceResponse<String>() {
                    @Override
                    public void onNext(String result) {
                        int count = Integer.valueOf(mTvLike.getText().toString()) + 1;
                        mTvLike.setText(String.format("%s", count));
                        mIvLike.setImageResource(R.mipmap.ic_like_checked);
                    }
                });
    }

    private void toUserInfo(int userID) {
        Intent intent = new Intent(getContext(), UserInfoActivity.class);
        intent.putExtra("user_id", userID);
        getContext().startActivity(intent);
    }

}
