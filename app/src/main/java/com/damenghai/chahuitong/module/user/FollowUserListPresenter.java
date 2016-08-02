package com.damenghai.chahuitong.module.user;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.UserModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FollowUserListPresenter extends BaseListActivityPresenter<FollowUserListActivity, User> {

    private int mUserID;

    private int mType;

    @Override
    protected void onCreate(FollowUserListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUserID = getView().getIntent().getIntExtra("user_id", -1);
        mType = getView().getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void onCreateView(FollowUserListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getFollowList(1, mUserID, mType).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        UserModel.getInstance().getFollowList(getCurPage(), mUserID, mType).unsafeSubscribe(getMoreSubscriber());
    }
}
