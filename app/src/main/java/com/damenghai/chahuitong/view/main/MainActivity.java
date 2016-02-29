package com.damenghai.chahuitong.view.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.damenghai.chahuitong.adapter.MainFragmentAdapter;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.mall.CartActivity;
import com.damenghai.chahuitong.view.personal.PersonalActivity;
import com.umeng.update.UmengUpdateAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.btn_toolbar_search)
    Button mBtnSearch;

    @Bind(R.id.main_viewpager)
    ViewPager mViewPager;

    @Bind(R.id.main_tab_layout)
    TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initToolbar();

        checkUpdata();
    }

    private void checkUpdata() {
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.setUpdateListener(null);
        UmengUpdateAgent.update(this);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(PersonalActivity.class);
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openActivity(CartActivity.class);
                return true;
            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }


    private void initTab() {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), this);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) tab.setIcon(adapter.getIconResId(i));
        }
        mViewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
