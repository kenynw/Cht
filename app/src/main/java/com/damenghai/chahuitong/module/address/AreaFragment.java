package com.damenghai.chahuitong.module.address;

import android.view.ViewGroup;

import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.damenghai.chahuitong.model.bean.Area;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(AreaPresenter.class)
public class AreaFragment extends BaseListFragment<AreaPresenter, Area> {

    @Override
    public BaseViewHolder<Area> createViewHolder(ViewGroup parent, int viewType) {
        return new AreaPresenter.AreaViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setNoMoreAble(false);
    }
}
