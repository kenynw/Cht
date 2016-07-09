package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.module.personal.MessageActivity;
import com.damenghai.chahuitong.module.user.PopularListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainTracePresenter extends Presenter<MainTraceFragment> {

    public void showFindFriend() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), PopularListActivity.class);
            getView().startActivity(i);
        }
    }

    public void showUserMessage() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), MessageActivity.class);
            getView().startActivity(i);
        }
    }

    private boolean checkLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            Intent i = new Intent(getView().getActivity(), LoginActivity.class);
            getView().startActivity(i);
            return false;
        }
        return true;
    }

}
