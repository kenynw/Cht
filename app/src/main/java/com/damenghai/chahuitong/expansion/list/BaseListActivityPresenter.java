package com.damenghai.chahuitong.expansion.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.ViewGroup;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter.OnLoadMoreListener;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BaseListActivityPresenter<V extends BaseListActivity, M> extends Presenter<V>
        implements OnLoadMoreListener, OnRefreshListener {

    private DataAdapter mAdapter;

    private int mPage = 1;

    private Subscriber<BeanList<M>> mRefreshSubscriber = new ServiceResponse<BeanList<M>>() {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getView().stopRefresh();
            getView().showError();
        }

        @Override
        public void onNext(BeanList<M> beanList) {
            mAdapter.clear();
            mAdapter.addAll(beanList.getList());
            if (beanList.isHasmore()) mPage = 2;
            else if (getView().getListConfig().mLoadMoreAble) mAdapter.stopMore();
        }
    };

    private Subscriber<BeanList<M>> mMoreSubscriber = new ServiceResponse<BeanList<M>>() {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mAdapter.pauseMore();
        }

        @Override
        public void onNext(BeanList<M> beanList) {
            mAdapter.addAll(beanList.getList());
            mPage++;
        }
    };

    public Subscriber<BeanList<M>> getRefreshSubscriber() {
        return mRefreshSubscriber;
    }

    public Subscriber<BeanList<M>> getMoreSubscriber() {
        return mMoreSubscriber;
    }

    public DataAdapter createAdapter() {
        return mAdapter = new DataAdapter(getView());
    }

    public DataAdapter getAdapter() {
        if (mAdapter == null) mAdapter = new DataAdapter(getView());
        return mAdapter;
    }

    public int getCurPage() {
        return mPage;
    }

    public void setCurPage(int page) {
        this.mPage = page;
    }

    @Override
    public void onLoadMore() {}

    @Override
    public void onRefresh() {}

    public class DataAdapter extends RecyclerArrayAdapter<M> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getViewType(int position) {
            return getView().getViewType(position);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return getView().createViewHolder(parent, viewType);
        }

    }

}
