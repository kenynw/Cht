package com.damenghai.chahuitong.presenter;

import com.damenghai.chahuitong.model.bean.Valuator;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.repository.ValuerRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.special.ValuerListMvp;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ValuerPresenter extends BasePresenter<ValuerListMvp> {
    private static final String TAG = "ValuerPresenter";

    private ValuerRepository mRepository;

    public ValuerPresenter() {
        mRepository = mRetrofit.create(ValuerRepository.class);
    }

    private String getCateSort() {
        return getView().getCateSort();
    }

    private int getPriceSort() {
        return getView().getPriceSort();
    }

    private int getSalesSort() {
        return getView().getSalesSort();
    }

    private int getSeasonSort() {
        return getView().getClickSort();
    }

    public void loadList() {
        int page = getView().getCurPage();

        if (page == 1) {
            getView().clear();
        }

        L.d(TAG, "page: " + page + ", cate: " + getCateSort() + ", price: " + getPriceSort() +
                ", sales: " + getSalesSort() + ", season: " + getSeasonSort());

        mRepository.list(page, getCateSort(), getPriceSort(), getSalesSort(), getSeasonSort())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Valuator>>() {

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
                        L.d(e.getLocalizedMessage() + e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(Response<Valuator> response) {
                        if (response.isSuccess()) {
                            getView().showRecommendList(response.getContent().getTastersRecommends());

                            getView().setCategory(response.getContent().getGoods_class());
                        } else {
                            getView().showError(response.getMsg());
                        }
                    }

                });
    }

}
