package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.bean.MessageCount;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.personal.MessageActivity;
import com.damenghai.chahuitong.module.user.PopularListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainTracePresenter extends BaseDataFragmentPresenter<MainTraceFragment, MessageCount> {

    private static final int REQUEST_MSG = 0;

    @Override
    protected void onCreateView(MainTraceFragment view) {
        super.onCreateView(view);
        ServiceClient.getServices().newMsgCount(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(getSubscriber());
    }

    public void showFindFriend() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), PopularListActivity.class);
            getView().startActivity(i);
        }
    }

    public void showUserMessage() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), MessageActivity.class);
            getView().startActivityForResult(i, REQUEST_MSG);
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

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MSG) {
            ServiceClient.getServices().newMsgCount(LUtils.getPreferences().getString("key", ""))
                    .compose(new DefaultTransform<>())
                    .subscribe(new ServiceResponse<MessageCount>() {
                        @Override
                        public void onNext(MessageCount messageCount) {
                            getView().setData(messageCount);
                        }
                    });
        }
    }
}
