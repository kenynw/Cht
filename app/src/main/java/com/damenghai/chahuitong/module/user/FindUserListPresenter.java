package com.damenghai.chahuitong.module.user;

import android.text.TextUtils;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FindUserListPresenter extends BaseListActivityPresenter<FindUserListActivity, User> {
    @Override
    protected void onCreateView(FindUserListActivity view) {
        super.onCreateView(view);
        getView().getToolbar().setOnMenuItemClickListener(item -> {
            if (TextUtils.isEmpty(getView().getName())) LUtils.toast("搜索内容不能为空");
            else {
                onRefresh();
                LUtils.closeKeyboard(getView().getEtSearch());
            }
            return true;
        });
    }

    @Override
    public void onRefresh() {
        FriendModel.getInstance().findList(getView().getName().trim(), 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        FriendModel.getInstance().findList(getView().getName().trim(), getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
