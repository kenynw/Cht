package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.FavoritesFragmentAdapter;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

@RequiresPresenter(FavoritesPresenter.class)
public class FavoritesActivity extends BeamBaseActivity<FavoritesPresenter> {

    @Bind(R.id.pager_favorites)
    ViewPager mPager;

    @Bind(R.id.tab_favorites)
    TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_favorites);
        setToolbarTitle(R.string.title_activity_favorites);
        ButterKnife.bind(this);

        mPager.setAdapter(new FavoritesFragmentAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }
}
