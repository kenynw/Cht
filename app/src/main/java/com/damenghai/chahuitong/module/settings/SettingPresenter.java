package com.damenghai.chahuitong.module.settings;

import android.content.Intent;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.MemberModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.main.MainActivity;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.utils.DataCleanManager;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SettingPresenter extends Presenter<SettingsActivity> {

    @Override
    protected void onCreateView(SettingsActivity view) {
        super.onCreateView(view);
        setData();
    }

    private void setData() {
        try {
            getView().setCache(DataCleanManager.getCacheSize(getView().getExternalCacheDir()));
            String versionName = getView().getPackageManager().getPackageInfo(getView().getPackageName(), 0).versionName;
            getView().setVersion(versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFeedback() {
        Intent i = new Intent(getView(), FeedbackActivity.class);
        getView().startActivity(i);
    }

    public void showAboutUs() {
        Intent i = new Intent(getView(), AboutActivity.class);
        getView().startActivity(i);
    }

    public void clearCache() {
        DataCleanManager.cleanExternalCache(getView());
        LUtils.toast(R.string.toast_clean_success);
        setData();
    }

    public void checkUpdate() {
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateResponse) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(getView(), updateResponse);
                        break;
                    case UpdateStatus.No: // has no update
                        LUtils.toast(R.string.toast_not_update);
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        LUtils.toast(R.string.toast_update_no_wifi);
                        break;
                    case UpdateStatus.Timeout: // time out
                        LUtils.toast(R.string.toast_network_timeout);
                        break;
                }
            }
        });
        UmengUpdateAgent.forceUpdate(getView());
    }

    public void logout() {
        DialogFactory.createGenericDialog(getView(), R.string.dialog_logout, (dialog, which) -> {
            MemberModel.getInstance().logout().subscribe(new ServiceResponse<String>() {
                @Override
                public void onNext(String s) {
                    if (LUtils.getPreferences().edit().remove("key").commit()) {
                        LUtils.toast(R.string.toast_logout_success);
                        Intent intent = new Intent(getView(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getView().startActivity(intent);
                    }
                }
            });
        }).show();
    }

}
