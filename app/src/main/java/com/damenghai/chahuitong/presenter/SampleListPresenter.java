package com.damenghai.chahuitong.presenter;

import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.repository.SampleRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.module.special.SampleListMvpView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SampleListPresenter extends BasePresenter<SampleListMvpView> {
    private static final String TAG = "SampleListPresenter";

    private SampleRepository mRepository;

    public SampleListPresenter() {
        mRepository = mRetrofit.create(SampleRepository.class);
    }

    public void showList() {
        mRepository.showList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<Sample>>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        getView().showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d(TAG, "error: " + e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(Response<List<Sample>> listResponse) {
                        L.d("msg: " + listResponse.getMsg() + ", name: " + listResponse.getContent().get(0).getSample_name());

                        if (listResponse.isSuccess()) {
                            getView().showList(listResponse.getContent());
                        } else {
                            getView().showError(listResponse.getMsg());
                        }
                    }
                });
    }


}
