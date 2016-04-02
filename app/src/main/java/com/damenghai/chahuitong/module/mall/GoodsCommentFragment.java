package com.damenghai.chahuitong.module.mall;

import com.damenghai.chahuitong.adapter.CommentListAdapter;
import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Comment;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsCommentFragment extends BaseListFragment {

    private List<Comment> mData;

    public GoodsCommentFragment() {}

    public static GoodsCommentFragment newInstance(List<Comment> data) {
        GoodsCommentFragment fragment = new GoodsCommentFragment();
        fragment.mData = data;
        return fragment;
    }

    @Override
    public void loadData() {
        getRecycleView().setAdapter(new CommentListAdapter(getActivity(), mData));
    }

}
