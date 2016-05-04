package com.damenghai.chahuitong.module.mall;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.CommentViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.widget.DividerItemDecoration;
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

    @Override
    public int getEmptyRes() {
        return R.layout.fragment_empty;
    }

    @Override
    public int getLoadMoreRes() {
        return R.layout.footer_load_more;
    }

    @Override
    public int getNoMoreRes() {
        return R.layout.footer_no_more;
    }

    @Override
    public DividerItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
    }
}
