package com.damenghai.chahuitong.module.user;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.MemberModel;
import com.damenghai.chahuitong.model.bean.User;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FollowUserListPresenter extends BaseListActivityPresenter<FollowUserListActivity, User> {

    private int mUserID;

    @Override
    protected void onCreate(FollowUserListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUserID = getView().getIntent().getIntExtra("user_id", 0);
    }

    @Override
    protected void onCreateView(FollowUserListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MemberModel.getInstance().getFollowerList(mUserID).subscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }
}
