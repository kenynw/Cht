package com.damenghai.chahuitong.module.settings;

import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.AccountModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.main.MainActivity;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.utils.DataCleanManager;
import com.damenghai.chahuitong.utils.LUtils;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showModify() {
        Intent i = new Intent(getView(), ModifyPwdActivity.class);
        getView().startActivity(i);
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

    public void logout() {
        new AlertDialog.Builder(getView())
                .setTitle(R.string.dialog_title_logout)
                .setMessage(R.string.dialog_logout)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_logout, (dialogInterface, i) -> {
                    AccountModel.getInstance().logout().subscribe(new ServiceResponse<String>() {
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
                })
                .show();
    }

}
