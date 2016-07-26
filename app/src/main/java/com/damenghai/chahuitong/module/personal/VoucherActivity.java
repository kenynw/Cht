package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.adapter.TitlePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VoucherActivity extends BaseActivity {
    private final int[] TITLE = new int[] {
            R.string.tab_voucher_unused,
            R.string.tab_voucher_used,
            R.string.tab_voucher_valid
    };

    @Bind(R.id.tab_layout)
    TabLayout mTab;

    @Bind(R.id.pager_voucher)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_voucher);
        setToolbarTitle(R.string.title_activity_voucher);
        ButterKnife.bind(this);

        initTab();
    }

    private void initTab() {
        int[] states = new int[] {1, 2, 3};
        List<Fragment> fragments = new ArrayList<>();
        for (int state : states) {
            fragments.add(VoucherFragment.newInstance(state));
        }
        TitlePagerAdapter adapter = new TitlePagerAdapter(this, TITLE, fragments, getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);
        mTab.setTabsFromPagerAdapter(adapter);
    }

}
