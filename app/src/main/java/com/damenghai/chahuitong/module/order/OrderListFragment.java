package com.damenghai.chahuitong.module.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.OrderListAdapter;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.utils.LUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(OrderListPresenter.class)
public class OrderListFragment extends BeamFragment<OrderListPresenter> {

    @Bind(R.id.fragment_list_view)
    PullToRefreshListView mPlv;

    @Bind(R.id.tv_list_empty)
    TextView mTvEmpty;

    private OrderListAdapter mAdapter;

    public static OrderListFragment get(String orderState) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", orderState);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        ButterKnife.bind(this, view);

        mPlv.setOnRefreshListener(getPresenter());
        mPlv.setOnLastItemVisibleListener(getPresenter());

        return view;
    }

    public void showList(List<Order> list) {
        if (list == null || list.size() <= 0) {
            mTvEmpty.setText(R.string.text_order_empty);
            mTvEmpty.setVisibility(View.VISIBLE);
        } else {
            if (mAdapter == null) {
                mAdapter = new OrderListAdapter(getActivity(), list, getPresenter(), R.layout.item_list_order);
            } else {
                mAdapter.addList(list);
            }
            mPlv.setAdapter(mAdapter);
        }

        if (mPlv.isRefreshing()) mPlv.onRefreshComplete();
    }

    public void showEmpty() {
        mTvEmpty.setText(R.string.text_order_empty);
        mTvEmpty.setVisibility(View.VISIBLE);
        mPlv.setVisibility(View.GONE);
    }

    public void cancelSuccess() {
        LUtils.toast(R.string.toast_cancel_success);
    }

    public void sureSuccess() {
        LUtils.toast(R.string.toast_sure_success);
    }

    public BaseListAdapter<Order> getAdapter() {
        return mAdapter;
    }

    public void clearData() {
        if (null != mAdapter) {
            mAdapter.clear();
        }
    }

}
