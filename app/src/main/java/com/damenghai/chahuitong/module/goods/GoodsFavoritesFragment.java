package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsFavoritesViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.damenghai.chahuitong.model.bean.Goods;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(GoodsFavoritesPresenter.class)
public class GoodsFavoritesFragment extends BaseListFragment<GoodsFavoritesPresenter, Goods> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public BaseViewHolder<Goods> createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsFavoritesViewHolder(parent, getPresenter());
    }

    @Override
    public ListConfig getListConfig() {
        View view = View.inflate(getActivity(), R.layout.empty_favorites_list, null);
        view.setOnClickListener(v -> getPresenter().showGoodsList());
        return super.getListConfig().setContainerEmptyView(view);
    }

}
