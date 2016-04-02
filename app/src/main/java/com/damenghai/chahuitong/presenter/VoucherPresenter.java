package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.VoucherRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.module.personal.VoucherMvpView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherPresenter extends BasePresenter<VoucherMvpView> {

    private VoucherRepository mRepository;

    private String mKey;

    public VoucherPresenter(Context context) {
        mRepository = mRetrofit.create(VoucherRepository.class);
        mKey = new PreferenceHelper(context).readSession();
    }

    public void list() {
        if (TextUtils.isEmpty(mKey)) {
            getView().toLogin();
            return;
        }

        mRepository.list(mKey, getView().getState())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<Voucher>>>() {
                    @Override
                    public void onCompleted() {
                        L.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d(e.getLocalizedMessage() + ", message: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<List<Voucher>> response) {
                        if (response.isSuccess() && response.getContent() != null) {
                            getView().showList(response.getContent());
                        } else {
                            getView().showError(response.getMsg());
                        }
                    }
                });
    }

}
