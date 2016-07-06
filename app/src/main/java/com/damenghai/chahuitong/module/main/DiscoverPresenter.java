package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Discover;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.module.article.ArticleListActivity;
import com.damenghai.chahuitong.module.flea.FleaListActivity;
import com.damenghai.chahuitong.module.mall.CategoryListActivity;
import com.damenghai.chahuitong.module.trace.TraceListActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class DiscoverPresenter extends BaseDataFragmentPresenter<DiscoverFragment, Discover> {
    @Override
    protected void onCreateView(DiscoverFragment view) {
        super.onCreateView(view);
        ServiceClient.getServices().discover()
                .compose(new DefaultTransform<>())
                .subscribe(getSubscriber());
    }

    private boolean checkLogin() {
        return !TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""));
    }

    public void showNews() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), ArticleListActivity.class);
            intent.putExtra("commend", 1);
            getView().startActivity(intent);
        }
    }

    public void showHill() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), TraceListActivity.class);
            intent.putExtra("commend", 1);
            getView().startActivity(intent);
        }
    }

    public void showTraceCommend() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), TraceListActivity.class);
            intent.putExtra("commend", 1);
            getView().startActivity(intent);
        }
    }

    public void showFleaList() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), FleaListActivity.class);
            getView().startActivity(intent);
        }
     }

    public void showCategoryList() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), CategoryListActivity.class);
            getView().startActivity(intent);
        }
    }

    public void showUserList() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), CategoryListActivity.class);
            getView().startActivity(intent);
        }
    }
}
