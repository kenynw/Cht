package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TraceFragmentAdapter;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.model.bean.MessageCount;
import com.damenghai.chahuitong.module.trace.TraceAddActivity;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainTracePresenter.class)
public class MainTraceFragment extends BaseDataFragment<MainTracePresenter, MessageCount> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.tab_main_trace)
    TabLayout mTab;

    @Bind(R.id.pager_main_trace)
    ViewPager mPager;

    @Bind(R.id.fbtn_main_trace_add)
    FloatingActionButton mBtnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_trace, container, false);
        ButterKnife.bind(this, view);

        initTab();
        initToolbar();

        mBtnAdd.setOnClickListener(v -> startActivity(new Intent(getActivity(), TraceAddActivity.class)));

        return view;
    }

    @Override
    public void setData(MessageCount messageCount) {
        getActivity().supportInvalidateOptionsMenu();
    }

    private void initTab() {
        TraceFragmentAdapter adapter = new TraceFragmentAdapter(getFragmentManager());
        mPager.setAdapter(adapter);
        for (int i=0; i<adapter.getCount(); i++) {
            mTab.addTab(mTab.newTab().setText(adapter.getPageTitle(i)));
        }
        mTab.setupWithViewPager(mPager);
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main_trace);
        mToolbar.setNavigationOnClickListener(v -> getPresenter().showFindFriend());

        MenuItem item = mToolbar.getMenu().findItem(R.id.action_message);
        TextView tv = (TextView) item.getActionView().findViewById(R.id.tv_action_msg);
        tv.setOnClickListener(v -> getPresenter().showUserMessage());

        if (getPresenter().getDataSubject().getValue() != null && getPresenter().getDataSubject().getValue().getCount_new_msg() > 0) {
            item.getActionView().findViewById(R.id.view_msg_badge).setVisibility(View.VISIBLE);
        } else {
            item.getActionView().findViewById(R.id.view_msg_badge).setVisibility(View.GONE);
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
