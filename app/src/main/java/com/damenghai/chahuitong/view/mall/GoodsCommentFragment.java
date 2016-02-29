package com.damenghai.chahuitong.view.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.CommentListAdapter;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Comment;
import com.damenghai.chahuitong.utils.LUtils;

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
