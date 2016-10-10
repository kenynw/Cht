package com.damenghai.chahuitong.module.goods;

import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.GoodsCardViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Goods;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(GoodsListFragmentPresenter.class)
public class GoodsListFragment extends BaseListFragment<GoodsListFragmentPresenter, Goods> {

    @Override
    public BaseViewHolder<Goods> createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsCardViewHolder(parent);
    }

}
