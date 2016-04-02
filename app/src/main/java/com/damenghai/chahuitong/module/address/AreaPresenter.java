package com.damenghai.chahuitong.module.address;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.AddressRepository;
import com.damenghai.chahuitong.presenter.BasePresenter;
import com.damenghai.chahuitong.utils.L;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AreaPresenter extends BasePresenter<AreaMvpView> {

    private AddressRepository mRepository;

    private PreferenceHelper mHelper;

    private String mToken;

    public AreaPresenter(Context context) {
        mRepository = mRetrofit.create(AddressRepository.class);
        mHelper = new PreferenceHelper(context);
    }

    public String getToken() {
        if (TextUtils.isEmpty(mToken)) {
            mToken = mHelper.readSession();
        }
        return mToken;
    }

    public void showAreaList() {
        if (TextUtils.isEmpty(getToken())) {
            return;
        }

        mRepository.areaList(getToken(), getView().getAreaId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ListResponse>>() {

                    @Override
                    public void onStart() {
                        getView().showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                        L.d(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ListResponse> jsonObject) {
                        ListResponse response = jsonObject.getDatas();
                        if (response.isError()) {
                            getView().showError(response.getError());
                        } else {
                            getView().setAreaList(response.getArea_list());
                        }
                    }
                });
    }

}
