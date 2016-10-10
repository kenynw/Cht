package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TitlePagerAdapter;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.module.goods.GoodsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainShopPresenter.class)
public class MainShopFragment extends BaseDataFragment<MainShopPresenter, List<Category>> {

    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.tv_toolbar_search)
    TextView mTvSearch;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.pager_main_shop)
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_shop, container, false);
        ButterKnife.bind(this, view);

        mToolbar.inflateMenu(R.menu.menu_main_mall);
        mToolbar.setOnMenuItemClickListener(item -> {
            getPresenter().toCart();
            return true;
        });
        mTvSearch.setOnClickListener(v -> startActivity(new Intent(getActivity(), SearchActivity.class)));

        return view;
    }

    @Override
    public void setData(List<Category> categories) {
        String[] titles = new String[categories.size() + 1];
        titles[0] = "全部";
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainHomeFragment());
        if (categories.size() > 0) {
            for (int i=0; i<categories.size(); i++) {
                Category category = categories.get(i);
                GoodsListFragment fragment = new GoodsListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("gc_id", category.getGc_id());
                fragment.setArguments(bundle);
                fragments.add(fragment);
                titles[i + 1] = category.getGc_name();
            }
        }

        TitlePagerAdapter adapter = new TitlePagerAdapter(getActivity(), titles, fragments, getFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
