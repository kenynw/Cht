package com.damenghai.chahuitong.view.special;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.base.TitlePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BargainActivity extends BaseActivity {
    private final int[] TITLE_RES = new int[] {
            R.string.tab_discount_nowadays,
            R.string.tab_discount_next
    };

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.pager_bargain)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargain);
        setToolbarTitle(R.string.title_activity_bargain);

        ButterKnife.bind(this);

        initTab();

    }

    private void initTab() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BargainListFragment.newInstance(0));
        fragments.add(BargainListFragment.newInstance(1));
        TitlePagerAdapter adapter = new TitlePagerAdapter(this, TITLE_RES, fragments, getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

}
