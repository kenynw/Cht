package com.damenghai.chahuitong.module.main;

import android.support.v4.widget.SwipeRefreshLayout;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class HomePresenter extends Presenter<HomeFragment> implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreateView(HomeFragment view) {
        super.onCreateView(view);
        getView().startRefresh();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().home(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<Home>() {
                    @Override
                    public void onCompleted() {
                        getView().stopRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().stopRefresh();
                    }

                    @Override
                    public void onNext(Home home) {
                        getView().setData(home);
                    }
                });
    }

}
