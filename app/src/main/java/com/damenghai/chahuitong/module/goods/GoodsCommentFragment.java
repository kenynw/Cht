package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(GoodsCommentPresenter.class)
public class GoodsCommentFragment extends BaseListFragment {

    public GoodsCommentFragment() {}

    public static GoodsCommentFragment newInstance(String goodsId) {
        GoodsCommentFragment fragment = new GoodsCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("goodsId", goodsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }
}
