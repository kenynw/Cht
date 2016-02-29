package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.FavoritesRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.personal.FavoritesMvpView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FavoritesPresenter extends BasePresenter<FavoritesMvpView> {

    private FavoritesRepository mRepository;

    private String mKey;

    public FavoritesPresenter(Context context) {
        mRepository = mRetrofit.create(FavoritesRepository.class);
        mKey = new PreferenceHelper(context).readSession();
    }

    @Override
    protected Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public void list() {
        mRepository.list(mKey)
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
                        getView().hideLoading();
                        L.d("onError" + e.getLocalizedMessage() + ", " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ListResponse> response) {
                        ListResponse data = response.getDatas();
                        if (data.isError()) {
                           getView().showError(data.getError());
                        } else if (data.getFavorites_list().size() > 0) {
                            getView().showList(data.getFavorites_list());
                        } else {
                            getView().showEmpty();
                        }
                    }
                });

    }

    public void delete() {
        if (TextUtils.isEmpty(mKey)) getView().toLogin();

        List<String> favList = getView().getFavIdList();

        Observable.from(favList)
                .flatMap(new Func1<String, Observable<Response<String>>>() {
                    @Override
                    public Observable<Response<String>> call(String s) {
                        return mRepository.delete(mKey, s);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<String>>() {
                    @Override
                    public void call(Response<String> response) {
                        if (response.getDatas().equals("1")) {
                            getView().operateSuccess();
                            list();
                        }
                    }
                });

    }

}
