package com.damenghai.chahuitong.module.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.module.personal.ProfileActivity;
import com.damenghai.chahuitong.utils.DataCleanManager;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.main.MainActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

    @Bind(R.id.tv_setting_cache)
    TextView mTvCache;

    private PreferenceHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_settings);
        setToolbarTitle(R.string.title_activity_settings);

        ButterKnife.bind(this);

        mHelper = new PreferenceHelper(this);

        setCacheSize();
    }

    private void setCacheSize() {
        try {
            String size = DataCleanManager.getCacheSize(getExternalCacheDir());
            mTvCache.setText(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toProfile(View view) {
        if (TextUtils.isEmpty(mHelper.readSession())) {
            openActivity(LoginActivity.class);
        } else {
            openActivity(ProfileActivity.class);
        }
    }

    public void toCleanCache(View view) {
        DialogFactory.createGenericDialog(this, R.string.dialog_clean_cache,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.cleanExternalCache(SettingsActivity.this);
                        setCacheSize();
                    }
                }).show();
    }

    public void toCheckUpdate(View view) {
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateResponse) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(SettingsActivity.this, updateResponse);
                        break;
                    case UpdateStatus.No: // has no update
                        showShort(R.string.toast_not_update);
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        showShort(R.string.toast_update_no_wifi);
                        break;
                    case UpdateStatus.Timeout: // time out
                        showShort(R.string.toast_network_timeout);
                        break;
                }
            }
        });
        UmengUpdateAgent.forceUpdate(this);
    }

    public void toAboutUs(View view) {
        openActivity(AboutActivity.class);
    }

    public void toLogout(View view) {
        DialogFactory.createGenericDialog(this, R.string.dialog_logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (LUtils.getPreferences().edit().remove("key").commit()) {
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    LUtils.toast(R.string.toast_logout_success);
                }
            }
        }).show();
    }

}
