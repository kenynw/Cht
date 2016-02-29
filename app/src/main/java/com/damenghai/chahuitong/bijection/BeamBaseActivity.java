package com.damenghai.chahuitong.bijection;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.overlay.DefaultViewExpansionDelegate;
import com.damenghai.chahuitong.expansion.overlay.ViewExpansionDelegate;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BeamBaseActivity<P extends Presenter> extends BeamAppCompatActivity<P> {

    private FrameLayout mContentParent;

    private Toolbar mToolbar;

    private ViewExpansionDelegate mDelegate;

    @Override
    public void preCreatePresenter() {
        mContentParent = (FrameLayout) findViewById(android.R.id.content);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) onSetToolbar();
    }

    private void onSetToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setToolbarTitle(int title) {
        if (mToolbar == null) {
            return;
        }
        TextView tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvTitle.setText(title);
    }

    public FrameLayout getContent() {
        return mContentParent;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public ViewExpansionDelegate getExpansionDelegate() {
        if (mDelegate == null) mDelegate = new DefaultViewExpansionDelegate(this);
        return mDelegate;
    }

}
