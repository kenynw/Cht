package com.damenghai.chahuitong.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.FleaGalleyAdapter;
import com.damenghai.chahuitong.adapter.TraceGridAdapter;
import com.damenghai.chahuitong.adapter.UserGridAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.model.bean.Discover;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(DiscoverPresenter.class)
public class DiscoverFragment extends BaseDataFragment<DiscoverPresenter, Discover> {

    @Bind(R.id.pager_discover_ad)
    ViewPager mPagerAd;

    @Bind(R.id.ll_discover_news)
    LinearLayout mLlNews;

    @Bind(R.id.ll_discover_hill)
    LinearLayout mLlHill;

    @Bind(R.id.tv_discover_trace)
    TextView mTvTrace;

    @Bind(R.id.rcv_discover_trace)
    RecyclerView mRcvTrace;

    @Bind(R.id.btn_discover_trace)
    Button mBtnTrace;

    @Bind(R.id.tv_discover_flea)
    TextView mTvFlea;

    @Bind(R.id.rcv_discover_flea)
    RecyclerView mRcvFlea;

    @Bind(R.id.tv_discover_class)
    TextView mTvClass;

    @Bind(R.id.rcv_discover_class)
    RecyclerView mRcvClass;

    @Bind(R.id.btn_discover_class)
    Button mBtnClass;

    @Bind(R.id.tv_discover_user)
    TextView mTvUser;

    @Bind(R.id.rcv_discover_user)
    RecyclerView mRcvUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_discover, container, false);
        ButterKnife.bind(this, view);

        mLlNews.setOnClickListener(v -> getPresenter().showNews());
        mLlHill.setOnClickListener(v -> getPresenter().showHill());
        mTvTrace.setOnClickListener(v -> getPresenter().showTraceCommend());
        mBtnTrace.setOnClickListener(v -> getPresenter().showTraceCommend());
        mTvFlea.setOnClickListener(v -> getPresenter().showFleaList());
        mTvClass.setOnClickListener(v -> getPresenter().showCategoryList());
        mBtnClass.setOnClickListener(v -> getPresenter().showCategoryList());
        mTvUser.setOnClickListener(v -> getPresenter().showUserList());

        mRcvTrace.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRcvFlea.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRcvUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void setData(Discover discover) {
        mRcvTrace.setAdapter(new TraceGridAdapter(getActivity(), discover.getTrace_list()));
        mRcvFlea.setAdapter(new FleaGalleyAdapter(getActivity(), discover.getFlea_list()));
        mRcvUser.setAdapter(new UserGridAdapter(getActivity(), discover.getMember_list()));
    }

}
