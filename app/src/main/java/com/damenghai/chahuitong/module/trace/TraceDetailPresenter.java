package com.damenghai.chahuitong.module.trace;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.LUtils;


/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceDetailPresenter extends BaseDataActivityPresenter<TraceDetailActivity, Trace> implements TextWatcher {

    private int mTraceId;

    @Override
    protected void onCreateView(TraceDetailActivity view) {
        super.onCreateView(view);
        mTraceId = getView().getIntent().getIntExtra("trace_id", 0);
        TraceModel.getInstance().getTraceDetail(mTraceId).subscribe(getDataSubscriber());
    }

    public void addFollow() {
        FriendModel.getInstance().addFollow(getDataSubject().getValue().getTrace_memberid())
                .subscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer relation) {
                        Trace trace = getDataSubject().getValue();
                        trace.setRelation(relation);
                        getView().setRelation(trace);
                    }
                });
    }

    public void delFollow() {
        FriendModel.getInstance().delFollow(getDataSubject().getValue().getTrace_memberid())
                .subscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer s) {
                        Trace trace = getDataSubject().getValue();
                        trace.setRelation(1);
                        getView().setRelation(trace);
                    }
                });
    }

    public void addComment(String content) {
        TraceModel.getInstance().addComment(mTraceId, content)
                .subscribe(new ServiceResponse<String>() {
                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                        LUtils.toast(s);
                    }
                });
    }

    public void addLike() {
        TraceModel.getInstance().addTraceLike(mTraceId).subscribe(new ServiceResponse<>());
    }

    public void showUserInfo(int userId) {
        Intent i = new Intent(getView(), UserInfoActivity.class);
        i.putExtra("userId", userId);
        getView().startActivity(i);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            getView().getSubmitBtn().setEnabled(true);
        } else {
            getView().getSubmitBtn().setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

}
