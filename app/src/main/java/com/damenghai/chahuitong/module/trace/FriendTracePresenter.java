package com.damenghai.chahuitong.module.trace;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.service.ServiceException;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FriendTracePresenter extends BaseListFragmentPresenter<FriendTraceFragment, Trace> {

    @Override
    protected void onCreateView(FriendTraceFragment view) {
        super.onCreateView(view);

        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            View error = View.inflate(getView().getActivity(), R.layout.error_login_trace_list, null);
            error.setOnClickListener(v -> toLogin());
            getView().getListView().setErrorView(error);
            getView().getListView().showError();
        } else {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        TraceModel.getInstance().getFriendTraceList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getFriendTraceList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

    public void toLogin() {
        Intent i = new Intent(getView().getActivity(), LoginActivity.class);
        getView().startActivity(i);
    }

}
