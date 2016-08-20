package com.damenghai.chahuitong.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.umeng.analytics.MobclickAgent;

import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private Toolbar mToolbar;

    protected IndeterminateProgressDrawable mProgressDrawable;

    protected ProgressBar mProgressBar;

    private FrameLayout mContentParent;

    @Override
    public void setContentView(int layoutResID) {
        mContentParent = (FrameLayout) findViewById(android.R.id.content);
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) setToolbar();
    }

    public FrameLayout getParentView() {
        return mContentParent;
    }

    private void setToolbar() {
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

    public void setToolbarTitle(String title) {
        if (mToolbar == null) {
            return;
        }
        TextView tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvTitle.setText(title);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showProgressBar() {
        if (mProgressDrawable == null) mProgressDrawable = new IndeterminateProgressDrawable(this);
        if (mProgressBar == null) mProgressBar = createProgressBar(this, mProgressDrawable);
        if (mProgressBar.getVisibility() != View.VISIBLE) mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        if (mProgressDrawable != null && mProgressDrawable.isRunning()) {
            mProgressDrawable.stop();
        }
        if (mProgressBar != null && mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
            mProgressBar = null;
        }
    }

    /**
     * 通过类名启动Activity
     * @param pClass
     */
    protected void openActivity(Class<? extends Activity> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity,并含有Bundle数据
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<? extends Activity> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if(pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action启动Activity
     * @param pAction
     */
    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 通过Action启动Activity,并含有Bundle数据
     * @param pAction
     * @param pBundle
     */
    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if(pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 回调函数启动Activity
     *
     * @param clazz
     * @param requestCode
     */
    protected void openActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        openActivityForResult(clazz, requestCode, null);
    }

    protected void openActivityForResult(Class<? extends Activity> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if(bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }


    /**
     * 在屏幕上添加一个转动的小菊花（传说中的Loading），默认为隐藏状态
     * 注意：务必保证此方法在setContentView()方法后调用，否则小菊花将会处于最底层，被屏幕其他View给覆盖
     *
     * @param activity                    需要添加菊花的Activity
     * @param customIndeterminateDrawable 自定义的菊花图片，可以为null，此时为系统默认菊花
     * @return {ProgressBar}    菊花对象
     */
    private ProgressBar createProgressBar(Activity activity, Drawable customIndeterminateDrawable) {
        // 给progressbar准备一个FrameLayout的LayoutParams
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置对其方式为：屏幕居中对其
        lp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        ProgressBar progressBar = new ProgressBar(activity);
        progressBar.setVisibility(View.GONE);
        progressBar.setLayoutParams(lp);
        // 自定义小菊花
        if (customIndeterminateDrawable != null) {
            progressBar.setIndeterminateDrawable(customIndeterminateDrawable);
        }

        // activity根部的ViewGroup，其实是一个FrameLayout
        FrameLayout rootContainer = (FrameLayout) activity.findViewById(android.R.id.content);
        // 将菊花添加到FrameLayout中
        rootContainer.addView(progressBar);
        return progressBar;
    }

    private void addEmptyLayout() {

        // 给progressbar准备一个FrameLayout的LayoutParams
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置对其方式为：屏幕居中对齐
        lp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        TextView tv = new TextView(this);
        tv.setText(R.string.text_order_empty);
        tv.setLayoutParams(lp);

        FrameLayout rootContainer = (FrameLayout) findViewById(android.R.id.content);
        rootContainer.addView(tv);
    }

    protected void showShort(String message) {
        LUtils.toast(message);
    }

    protected void showShort(@StringRes int res) {
        LUtils.toast(res);
    }

    @Override
    public void showLoading() {
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
    }

    @Override
    public void showError(String message) {
        showShort(message);
    }

    @Override
    public void toLogin() {
        openActivity(LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
