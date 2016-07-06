package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ArticleViewHolder extends BaseViewHolder<Article> {

    @Bind(R.id.dv_article_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_article_username)
    TextView mTvUser;

    @Bind(R.id.tv_article_title)
    TextView mTvTitle;

    @Bind(R.id.dv_article_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.tv_article_comment)
    TextView mTvComment;

    public ArticleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_article);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Article article) {
        mDvAvatar.setImageURI(Uri.parse(article.getArticle_publisher_avatar()));
        mTvUser.setText(article.getArticle_publisher_name());
        mTvTitle.setText(article.getArticle_title());
        mDvImage.setImageURI(Uri.parse(article.getArticle_image()));
        mTvComment.setText(String.format(getContext().getString(R.string.text_article_heat), article.getArticle_click(), article.getArticle_comment_count()));
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), WebViewActivity.class);
            i.putExtra("url", article.getArticle_url());
            getContext().startActivity(i);
        });
    }
}
