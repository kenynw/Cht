package com.damenghai.chahuitong.module.flea;

import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.FleaViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Flea;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(FleaFavoritesPresenter.class)
public class FleaFavoritesFragment extends BaseListFragment<FleaFavoritesPresenter, Flea> {

    @Override
    public BaseViewHolder<Flea> createViewHolder(ViewGroup parent, int viewType) {
        return new FleaViewHolder(parent);
    }

}
