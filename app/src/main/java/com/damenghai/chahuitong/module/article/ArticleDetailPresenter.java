package com.damenghai.chahuitong.module.article;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.ArticleModel;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.Services;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ArticleDetailPresenter extends BaseDataActivityPresenter<ArticleDetailActivity, Article> {

    private Article mArticle;

    @Override
    protected void onCreate(ArticleDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mArticle = getView().getIntent().getParcelableExtra("article");
    }

    @Override
    protected void onCreateView(ArticleDetailActivity view) {
        super.onCreateView(view);
        publishObject(mArticle);
    }

    public void addComment(EditText editText) {
        ArticleModel.getInstance().addComment(mArticle.getArticle_id(), editText.getText().toString().trim())
                .unsafeSubscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer result) {
                        publishObject(mArticle);
                        LUtils.closeKeyboard(editText);
                        editText.getText().clear();
                    }
                });
    }

}
