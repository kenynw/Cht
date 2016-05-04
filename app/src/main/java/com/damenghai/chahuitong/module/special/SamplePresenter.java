package com.damenghai.chahuitong.module.special;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SamplePresenter extends BaseDataActivityPresenter<SampleActivity, Sample> {

    @Override
    protected void onCreateView(SampleActivity view) {
        super.onCreateView(view);
        setData();
    }

    public void setData() {
        ServiceClient.getServices().curSample(LUtils.getPreferences().getString("key", ""))
                .compose(new ServiceTransform<>())
                .subscribe(getDataSubscriber());
    }

}
