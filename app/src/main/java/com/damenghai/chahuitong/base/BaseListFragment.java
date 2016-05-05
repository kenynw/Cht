package com.damenghai.chahuitong.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.module.MvpView;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 *
 * A fragment representing a list of Items.
 */
public abstract class BaseListFragment extends BaseFragment implements MvpView {

    private RecyclerView mRecyclerView;

    private int mCurPage;

    private OnLastItemVisibleListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BaseListFragment() {}

    @Override
    public void showLoading() {
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int itemCount = layoutManager.getItemCount();
                    if (lastVisibleItem >= itemCount - 2 && dy > 0) {
                        mCurPage += 1;
                        if (mListener != null) mListener.onLastItemVisible();
                    }
                }
            });
            mCurPage = 1;
            loadData();
        }

        return view;
    }

    public RecyclerView getRecycleView() {
        return mRecyclerView;
    }

    public void setOnLastItemVisibleListener(OnLastItemVisibleListener listener) {
        mListener = listener;
    }

    public abstract void loadData();

    protected int getPage() {
        return mCurPage;
    }

    protected RecyclerView.Adapter getAdapter() {
        return mRecyclerView.getAdapter();
    }

    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

}
