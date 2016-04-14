package com.damenghai.chahuitong.expansion.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.widget.DividerItemDecoration;
import com.damenghai.chahuitong.widget.WrapHeightListManager;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseListActivity<P extends BaseListPresenter> extends BeamBaseActivity<P> {
    private EasyRecyclerView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initRecycle();
        initAdapter();
    }

    private void setContentView() {
        if (getLayout() != 0) {
            setContentView(getLayout());
        } else {
            mListView = new EasyRecyclerView(this);
            mListView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
            mListView.setId(R.id.recycle);
            setContentView(mListView);
        }
    }

    private void initRecycle() {
        if (mListView == null) mListView = (EasyRecyclerView) findViewById(R.id.recycle);
        if (mListView == null) throw new RuntimeException("No found RecycleView with id 'recycle'");
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mListView.setRefreshListener(getPresenter());
        if (getProgressRes() != 0) mListView.setProgressView(getProgressRes());
        if (getErrorRes() != 0) mListView.setErrorView(getErrorRes());
        if (getEmptyRes() != 0) mListView.setEmptyView(getEmptyRes());
    }

    private void initAdapter() {
        BaseListPresenter.DataAdapter adapter = getPresenter().getAdapter();
        mListView.setAdapterWithProgress(adapter);
        if (getLoadMoreRes() != 0) adapter.setMore(getLoadMoreRes(), getPresenter());
        if (getNoMoreRes() != 0) adapter.setNoMore(getNoMoreRes());
    }

    public void stopRefresh() {
        mListView.getSwipeToRefresh().setRefreshing(false);
    }

    public void showError() {
        mListView.showError();
    }

    protected int getLayout() {
        return 0;
    }

    protected int getProgressRes() {
        return 0;
    }

    protected int getErrorRes() {
        return 0;
    }

    protected int getEmptyRes() {
        return 0;
    }

    protected int getLoadMoreRes() {
        return 0;
    }

    protected int getNoMoreRes() {
        return 0;
    }

    public EasyRecyclerView getListView() {
        return mListView;
    }

    protected abstract BaseViewHolder createViewHolder(ViewGroup parent, int viewType);

}
