package com.damenghai.chahuitong.module.personal;

import android.content.Intent;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.MessageCount;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MessagePresenter extends BaseDataActivityPresenter<MessageActivity, MessageCount> {

    @Override
    protected void onCreateView(MessageActivity view) {
        super.onCreateView(view);
        ServiceClient.getServices().newMsgCount(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(getDataSubscriber());
    }

    public void showMsgList(int type, CharSequence title) {
        Intent i = new Intent(getView(), MessageListActivity.class);
        i.putExtra("type", type);
        i.putExtra("title", title);
        getView().startActivityForResult(i, 0);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
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
