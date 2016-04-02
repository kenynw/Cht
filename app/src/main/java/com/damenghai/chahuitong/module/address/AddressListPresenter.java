package com.damenghai.chahuitong.module.address;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.AddressRepository;
import com.damenghai.chahuitong.presenter.BasePresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.JsonObject;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddressListPresenter extends BasePresenter<AddressListMvpView> {

    private AddressRepository mRepository;

    private PreferenceHelper mHelper;

    private String mKey;

    public AddressListPresenter(Context context) {
        mRepository = mRetrofit.create(AddressRepository.class);
        mHelper = new PreferenceHelper(context);
        mKey = mHelper.readSession();
    }

    public void showList() {
        if (TextUtils.isEmpty(mKey)) {
            return;
        }

        mRepository.addressList(mKey)
                .subscribeOn(Schedulers.newThread())
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
                        LUtils.log(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Response<ListResponse> jsonObject) {
                        ListResponse data = jsonObject.getDatas();
                        if (data.isError()) {
                            getView().showError(data.getError());
                        } else {
                            getView().showList(data.getAddress_list());
                        }
                    }
                });
    }

    public void delete() {
        List<String> list = getView().getSelectedItems();

        if (list != null && list.size() > 0) {
            for (String id : list) {
                mRepository.del(mKey, id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<JsonObject>() {
                            @Override
                            public void call(JsonObject jsonObject) {
                                showList();
                            }
                        });
            }
        }
    }

}
