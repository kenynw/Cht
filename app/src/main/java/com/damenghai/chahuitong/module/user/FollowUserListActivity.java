package com.damenghai.chahuitong.module.user;

import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.UserViewHolder;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class FollowUserListActivity extends BaseListActivity<FollowerListPresenter> {

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_list;
    }

}
