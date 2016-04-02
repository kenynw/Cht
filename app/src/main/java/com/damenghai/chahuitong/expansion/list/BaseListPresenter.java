package com.damenghai.chahuitong.expansion.list;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.config.Config;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BaseListPresenter extends Presenter {

    public int getPageSize() {
        return Config.DEFAULT_PAGE_SIZE;
    }

}
