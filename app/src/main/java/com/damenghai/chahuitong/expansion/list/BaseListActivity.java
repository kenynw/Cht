package com.damenghai.chahuitong.expansion.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseListActivity<P extends BaseListActivityPresenter> extends BeamBaseActivity<P> {

    private EasyRecyclerView mListView;

    private ListConfig mListConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListConfig = getListConfig();
        setContentView();
        initRecycle();
        initAdapter();
    }

    private void setContentView() {
        if (getLayout() != 0) {
            setContentView(getLayout());
        } else {
            mListView = new EasyRecyclerView(this);
            mListView.setId(R.id.recycle);
            mListView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
            setContentView(mListView);
        }
    }

    private void initRecycle() {
        if (mListView == null) mListView = (EasyRecyclerView) findViewById(R.id.recycle);
        if (mListView == null) throw new RuntimeException("No found RecycleView with id 'recycle'");

        mListView.setLayoutManager(new LinearLayoutManager(this));

        if (mListConfig.mRefreshAble) mListView.setRefreshListener(getPresenter());
        if (mListConfig.mHasItemDecoration) {
            if (mListConfig.mItemDecoration != null) {
                mListView.addItemDecoration(mListConfig.mItemDecoration, mListConfig.mDecorationOrientation);
            } else {
                mListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            }
        }
        if (mListConfig.mContainerProgressAble) {
            if (mListConfig.mContainerProgressView != null) mListView.setProgressView(mListConfig.mContainerProgressView);
            else if (mListConfig.mContainerProgressRes != 0) mListView.setProgressView(mListConfig.mContainerProgressRes);
        }
        if (mListConfig.mContainerErrorAble) {
            if (mListConfig.mContainerErrorView != null) mListView.setProgressView(mListConfig.mContainerErrorView);
            else if (mListConfig.mContainerErrorRes != 0) mListView.setProgressView(mListConfig.mContainerErrorRes);
        }
        if (mListConfig.mContainerEmptyAble) {
            if (mListConfig.mContainerEmptyView != null) mListView.setProgressView(mListConfig.mContainerEmptyView);
            else if (mListConfig.mContainerEmptyRes != 0) mListView.setProgressView(mListConfig.mContainerEmptyRes);
        }
    }

    private void initAdapter() {
        BaseListActivityPresenter.DataAdapter adapter = getPresenter().getAdapter();
        mListView.setAdapterWithProgress(adapter);

        if (mListConfig.mFooterErrorAble) {
            View errorView = null;
            if (mListConfig.mFooterErrorView != null) errorView = adapter.setError(mListConfig.mFooterErrorView);
            else if (mListConfig.mFooterErrorRes > 0) errorView = adapter.setError(mListConfig.mFooterErrorRes);
            if (mListConfig.mErrorTouchToResumeAble && errorView != null) {
                errorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.resumeMore();
                    }
                });
            }
        }
        if (mListConfig.mLoadMoreAble) {
            if (mListConfig.mFooterMoreView != null) adapter.setMore(mListConfig.mFooterMoreView, getPresenter());
            else if (mListConfig.mFooterMoreRes > 0) adapter.setMore(mListConfig.mFooterMoreRes, getPresenter());
        }
        if (mListConfig.mNoMoreAble) {
            if (mListConfig.mFooterNoMoreView != null) adapter.setNoMore(mListConfig.mFooterNoMoreView);
            else if (mListConfig.mFooterNoMoreRes > 0) adapter.setNoMore(mListConfig.mFooterNoMoreRes);
        }
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

    public ListConfig getListConfig() {
        return ListConfig.DEFAULT.clone();
    }

    public EasyRecyclerView getListView() {
        return mListView;
    }

    public int getViewType(int position){
        return 0;
    }

    protected abstract BaseViewHolder createViewHolder(ViewGroup parent, int viewType);

}
