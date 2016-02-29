package com.damenghai.chahuitong.expansion.data;

import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(BaseDataActivityPresenter.class)
public class BaseDataActivity<T extends BaseDataActivityPresenter, M> extends BeamBaseActivity<T> {

    public void setData(M m) {}
    public void onError(Throwable e) {}
}
