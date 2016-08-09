package com.damenghai.chahuitong.module.article;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ArticleDetailPresenter extends BaseDataActivityPresenter<ArticleDetailActivity, Article> {

    private int mArticleID;

    @Override
    protected void onCreate(ArticleDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mArticleID = getView().getIntent().getIntExtra("article_id", 0);
    }

    @Override
    protected void onCreateView(ArticleDetailActivity view) {
        super.onCreateView(view);
        LUtils.log("id: " + mArticleID);
//        ServiceClient.getServices().articleDetail(LUtils.getPreferences().getString("key", ""), mArticleID)
//                .compose(new DefaultTransform<>())
//                .unsafeSubscribe(getDataSubscriber());
    }
}
