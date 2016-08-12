package com.damenghai.chahuitong.module.settings;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.model.CommonModel;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SettingPresenter.class)
public class SettingsActivity extends BeamBaseActivity<SettingPresenter> {

    @Bind(R.id.btn_setting_modify)
    Button mBtnModify;

    @Bind(R.id.btn_setting_feedback)
    Button mBtnFeedback;

    @Bind(R.id.tv_setting_cache)
    TextView mTvCache;

    @Bind(R.id.btn_setting_cache)
    Button mBtnCache;

    @Bind(R.id.btn_setting_update)
    Button mBtnUpdate;

    @Bind(R.id.tv_setting_version)
    TextView mTvVersion;

    @Bind(R.id.btn_setting_about)
    Button mBtnAbout;

    @Bind(R.id.btn_setting_logout)
    Button mBtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_settings);
        setToolbarTitle(R.string.title_activity_settings);
        ButterKnife.bind(this);

        mBtnModify.setOnClickListener(v -> getPresenter().showModify());
        mBtnFeedback.setOnClickListener(v -> getPresenter().showFeedback());
        mBtnAbout.setOnClickListener(v -> getPresenter().showAboutUs());
        mBtnCache.setOnClickListener(v -> getPresenter().clearCache());
        mBtnUpdate.setOnClickListener(v -> CommonModel.getInstance().checkUpdate(this));
        mBtnLogout.setOnClickListener(v -> getPresenter().logout());
        mTvVersion.setText(String.format("当前版本v%s", LUtils.getAppVersionName()));
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) mBtnLogout.setVisibility(View.GONE);
    }

    public void setCache(String s) {
        mTvCache.setText(s);
    }

}
