package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.damenghai.chahuitong.adapter.MainFragmentAdapter;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.mall.CartActivity;
import com.damenghai.chahuitong.module.personal.PersonalActivity;
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

    private long mTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initToolbar();

        initTab();

        checkUpdate();
    }

    private void checkUpdate() {
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

        mBtnSearch.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        long curTime = System.currentTimeMillis();
        if (curTime - mTime < 2000) {
            System.exit(0);
        } else {
            mTime = curTime;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long curTime = System.currentTimeMillis();
            if (curTime - mTime > 2000) {
                mTime = curTime;
                LUtils.toast(R.string.toast_exit);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
