package com.damenghai.chahuitong.view.settings;

import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_about);
        setToolbarTitle(R.string.title_activity_about);
    }
}
