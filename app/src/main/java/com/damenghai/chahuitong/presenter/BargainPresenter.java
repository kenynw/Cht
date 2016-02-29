package com.damenghai.chahuitong.presenter;

import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.repository.BargainRepository;
import com.damenghai.chahuitong.view.special.BargainMvpView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BargainPresenter extends BasePresenter<BargainMvpView> {
    BargainRepository mRepository;

    public BargainPresenter() {
        mRepository = mRetrofit.create(BargainRepository.class);
    }

    public void showList() {
        mRepository.showList(getView().getCurPage(), getView().getOp())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<Bargain>>>() {

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
                        getView().showError("没有数据");
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(Response<List<Bargain>> response) {
                        if (response.isSuccess()) {
                            getView().showList(response.getContent());
                        } else {
                            getView().showError(response.getMsg());
                        }
                    }
                });
    }

}
