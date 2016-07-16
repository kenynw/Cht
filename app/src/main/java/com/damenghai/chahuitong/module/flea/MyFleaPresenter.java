package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MyFleaPresenter extends BaseListActivityPresenter<MyFleaActivity, Flea> {

    @Override
    protected void onCreate(MyFleaActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(MyFleaActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        FleaModel.getInstance().getMyFleaList().unsafeSubscribe(getRefreshSubscriber());
    }
}
