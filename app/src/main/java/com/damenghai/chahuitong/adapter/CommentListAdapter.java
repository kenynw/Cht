package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Comment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    private Context mContext;

    private final List<Comment> mData;

    public CommentListAdapter(Context context, List<Comment> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = mData.get(position);
        holder.mTvContent.setText(comment.getGeval_content());
        holder.mTvName.setText(comment.getGeval_frommembername());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dv_comment_avatar)
        SimpleDraweeView mDvAvatar;

        @Bind(R.id.tv_comment_name)
        TextView mTvName;

        @Bind(R.id.tv_comment_date)
        TextView mTvDate;

        @Bind(R.id.tv_comment_content)
        TextView mTvContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
