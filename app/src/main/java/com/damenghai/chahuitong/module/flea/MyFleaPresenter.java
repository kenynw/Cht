package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

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
