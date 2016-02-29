package com.damenghai.chahuitong.view.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.OrderListAdapter;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.presenter.OrderListPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListFragment extends BaseFragment implements OrderListMvpView, OnLastItemVisibleListener,
        OnRefreshListener<ListView> {
    private final String KEY_ORDER_STATE = "OrderListFragment:OrderState";

    @Bind(R.id.fragment_list_view)
    PullToRefreshListView mPlv;

    @Bind(R.id.tv_list_empty)
    TextView mTvEmpty;

    private String mState;

    private int mCurPage;

    private OrderListPresenter mPresenter;

    private OrderListAdapter mAdapter;

    public static OrderListFragment get(String orderState) {
        OrderListFragment fragment = new OrderListFragment();
        fragment.mState = orderState;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        ButterKnife.bind(this, view);

        mPlv.setOnRefreshListener(this);
        mPlv.setOnLastItemVisibleListener(this);
        mPlv.getRefreshableView().setDividerHeight(8);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCurPage = 1;
        mPresenter.list();
    }

    @Override
    public void showList(List<Order> list) {
        if (list == null || list.size() <= 0) {
            mTvEmpty.setText(R.string.text_order_empty);
            mTvEmpty.setVisibility(View.VISIBLE);
        } else {
            if (mAdapter == null) {
                mAdapter = new OrderListAdapter(mContext, list, mPresenter, R.layout.item_list_order);
            } else {
                mAdapter.addList(list);
            }
            mPlv.setAdapter(mAdapter);
        }

    }

    @Override
    public void operateSuccess() {
        showShort(R.string.toast_operate_success);
    }

    @Override
    public void setRefreshing() {
        mPlv.setRefreshing();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_ORDER_STATE)) {
           mState = savedInstanceState.getString(KEY_ORDER_STATE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_ORDER_STATE, mState);
    }

    @Override
    public void onLastItemVisible() {
        mCurPage += 1;
        mPresenter.list();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        mCurPage = 1;
        mPresenter.list();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new OrderListPresenter(context);
        mPresenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public String getState() {
        return mState;
    }

    @Override
    public int getCurPage() {
        return mCurPage;
    }

    @Override
    public void clearData() {
        if (null != mAdapter) {
            mAdapter.clear();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        if (mPlv.isRefreshing()) mPlv.onRefreshComplete();
    }

}
