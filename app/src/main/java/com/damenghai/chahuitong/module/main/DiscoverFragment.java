package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.damenghai.chahuitong.adapter.CateImageAdapter;
import com.damenghai.chahuitong.adapter.FleaGalleyAdapter;
import com.damenghai.chahuitong.adapter.TraceImageAdapter;
import com.damenghai.chahuitong.adapter.UserGridAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.expansion.list.DividerGridItemDecoration;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.model.bean.Discover;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(DiscoverPresenter.class)
public class DiscoverFragment extends BaseDataFragment<DiscoverPresenter, Discover> {

    @Bind(R.id.toolbar_title)
    TextView mTvTitle;

    @Bind(R.id.pager_discover_ad)
    SimpleDraweeView mPagerAd;

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

        mTvTitle.setText(R.string.title_discover);
        mLlNews.setOnClickListener(v -> getPresenter().showNews());
        mLlHill.setOnClickListener(v -> getPresenter().showHill());
        mTvTrace.setOnClickListener(v -> getPresenter().showTraceCommend());
        mBtnTrace.setOnClickListener(v -> getPresenter().showTraceCommend());
        mTvFlea.setOnClickListener(v -> getPresenter().showFleaList());
        mTvClass.setOnClickListener(v -> getPresenter().showCategoryList());
        mBtnClass.setOnClickListener(v -> getPresenter().showCategoryList());
        mTvUser.setOnClickListener(v -> getPresenter().showUserList());

        mRcvTrace.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRcvTrace.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        mRcvFlea.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRcvFlea.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        mRcvUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mRcvClass.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRcvClass.addItemDecoration(new SpaceDecoration(LUtils.dp2px(8)));

        return view;
    }

    @Override
    public void setData(Discover discover) {
        mPagerAd.setImageURI(Uri.parse(discover.getAd_list().get(0).getLink_pic_url()));
        mPagerAd.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), GoodsListActivity.class);
            i.putExtra("keyword", discover.getAd_list().get(0).getLink_keyword());
            i.putExtra("op", "goods_list");
            getActivity().startActivity(i);
        });
        mRcvTrace.setAdapter(new TraceImageAdapter(getActivity(), discover.getTrace_list()));
        mRcvFlea.setAdapter(new FleaGalleyAdapter(getActivity(), discover.getFlea_list()));
        mRcvUser.setAdapter(new UserGridAdapter(getActivity(), discover.getMember_list()));
        mRcvClass.setAdapter(new CateImageAdapter(getActivity(), discover.getClass_list()));
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
