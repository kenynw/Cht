package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ArticleModel {
    private static ArticleModel mInstance;

    public static ArticleModel getInstance() {
        if (null == mInstance) {
            synchronized (ArticleModel.class) {
                if (null == mInstance) mInstance = new ArticleModel();
            }
        }
        return mInstance;
    }

    private ArticleModel() {}

    public Observable<BeanList<Article>> getArticleList(int page) {
        return ServiceClient.getServices().articleList(LUtils.getPreferences().getString("key", ""), page)
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> addComment(int articleID, String content) {
        return ServiceClient.getServices().articleAddComment(LUtils.getPreferences().getString("key", ""), articleID, content)
                .compose(new DefaultTransform<>());
    }

}
