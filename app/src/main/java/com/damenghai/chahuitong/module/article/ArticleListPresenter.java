package com.damenghai.chahuitong.module.article;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ArticleListPresenter extends BaseListActivityPresenter<ArticleListActivity, Article> {

    @Override
    protected void onCreate(ArticleListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().articleList(LUtils.getPreferences().getString("key", ""), 1)
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().articleList(LUtils.getPreferences().getString("key", ""), getCurPage())
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getMoreSubscriber());
    }

}
