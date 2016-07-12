package com.damenghai.chahuitong.module.flea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaListPresenter extends BaseListActivityPresenter<FleaListActivity, Flea> {

    @Override
    protected void onCreateView(FleaListActivity view) {
        super.onCreateView(view);
        getView().getToolbar().setOnMenuItemClickListener(item -> {
            Intent i = new Intent(getView(), FleaAddActivity.class);
            getView().startActivity(i);
            return true;
        });

        onRefresh();
    }

    @Override
    public void onRefresh() {
        FleaModel.getInstance().getFleaList().unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        FleaModel.getInstance().getFleaList().unsafeSubscribe(getMoreSubscriber());
    }

}
