package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.throwable.ResponseThrowable;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.repository.MainRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.main.HomeMvpView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class HomePresenter extends BasePresenter<HomeMvpView> {

    private MainRepository mRepository;

    private String mKey;

    public HomePresenter(Context context) {
        mRepository = mRetrofit.create(MainRepository.class);
        mKey = new PreferenceHelper(context).readSession();
    }

    public void loadHomeData() {
        checkAttachView();

        mSubscription = mRepository.home(mKey, getView().getCurPage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Home>>() {

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
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(Response<Home> homeResponse) {
                        if (homeResponse.isSuccess()) {
                            Home home = homeResponse.getContent();

                            if (getView().getCurPage() >= 2) {
                                getView().showRecommendForYou(home.getGuessYouLike());
                            } else {

                                getView().showBanner(home.getCarouselFigure());

                                getView().showSpecial(home.getGoodsSample(), home.getFlashSale());

                                getView().showRecommend(home.getTastersRecommends());

                                getView().showRecommendForYou(home.getGuessYouLike());
                            }
                        } else {
                            getView().showError(homeResponse.getMsg());
                        }
                    }

                });

    }

}
