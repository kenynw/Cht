package com.damenghai.chahuitong.view.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.base.TitlePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseActivity {
    private final int[] TITLE_RES = new int[] {
            R.string.tab_order_unpaid,
            R.string.tab_order_paid,
            R.string.tab_order_receive,
            R.string.tab_order_uncomment,
            R.string.tab_order_all
    };

    /**
     * 未付款
     */
    public static final int STATE_UNPAID = 10;
    /**
     * 已付款，待发货
     */
    public static final int STATE_PAID = 20;
    /**
     * 已发货，待收货
     */
    public static final int STATE_RECEIVE = 30;
    /**
     * 交易完成，待评价
     */
    public static final int STATE_UNCOMMENT = 40;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.pager_order_list)
    ViewPager mPager;

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        setToolbarTitle(R.string.title_my_order);
        ButterKnife.bind(this);

        mPosition = getIntent().getIntExtra("position", 0);

        initTab();
    }

    private void initTab() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OrderListFragment.get(STATE_UNPAID + ""));
        fragments.add(OrderListFragment.get(STATE_PAID + ""));
        fragments.add(OrderListFragment.get(STATE_RECEIVE + ""));
        fragments.add(OrderListFragment.get(STATE_UNCOMMENT + ""));
        fragments.add(OrderListFragment.get(""));
        TitlePagerAdapter adapter = new TitlePagerAdapter(this, TITLE_RES, fragments, getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mPager.setCurrentItem(mPosition, false);
    }

}
