package com.damenghai.chahuitong.module.goods;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SampleDetailPresenter extends BaseDataActivityPresenter<SampleDetailActivity, Goods> {

    @Override
    protected void onCreateView(SampleDetailActivity view) {
        super.onCreateView(view);
        setData();
    }

    public void setData() {
        ServiceClient.getServices().curSample(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(getDataSubscriber());
    }

}
