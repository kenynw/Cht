package com.damenghai.chahuitong.expansion.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.widget.DividerItemDecoration;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseListFragment<P extends BaseListFragmentPresenter, M> extends BeamFragment<P> {
    private View mRootView;

    private EasyRecyclerView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflate(container);
        initListView();
        initAdapter();
        return mRootView;
    }

    private void layoutInflate(@Nullable ViewGroup container) {
        if (getLayout() > 0) {
            mRootView = LayoutInflater.from(getActivity()).inflate(getLayout(), container, false);
        } else {
            mListView = new EasyRecyclerView(getActivity());
            mListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mListView.setId(R.id.recycle);
            mRootView = mListView;
        }
    }

    private void initListView() {
        if (mListView == null) mListView = (EasyRecyclerView) mRootView.findViewById(R.id.recycle);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (getItemDecoration() != null) mListView.addItemDecoration(getItemDecoration());
        mListView.setRefreshListener(getPresenter());
        if (getErrorRes() > 0) mListView.setErrorView(getErrorRes());
        if (getEmptyRes() > 0) mListView.setEmptyView(getEmptyRes());
        if (getProgressRes() > 0) mListView.setProgressView(getProgressRes());
    }

    private void initAdapter() {
        BaseListFragmentPresenter.DataAdapter adapter = getPresenter().getAdapter();
        mListView.setAdapterWithProgress(adapter);
        if (getLoadMoreRes() > 0) adapter.setMore(getLoadMoreRes(), getPresenter());
        if (getNoMoreRes() > 0) adapter.setNoMore(getNoMoreRes());
    };

    public EasyRecyclerView getListView() {
        return mListView;
    }

    public void stopRefresh() {
        mListView.setRefreshing(false);
    }

    public void showError() {}

    public int getLayout() {
        return 0;
    }

    public int getErrorRes() {
        return 0;
    }

    public int getEmptyRes() {
        return 0;
    }

    public int getProgressRes() {
        return 0;
    }

    public int getLoadMoreRes() {
        return 0;
    }

    public int getNoMoreRes() {
        return 0;
    }

    public DividerItemDecoration getItemDecoration() {
        return null;
    }

    public int getViewType(int position) {
        return 0;
    }

    public abstract BaseViewHolder<M> createViewHolder(ViewGroup parent, int viewType);
}
