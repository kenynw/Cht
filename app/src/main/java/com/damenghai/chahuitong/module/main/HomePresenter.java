package com.damenghai.chahuitong.module.main;

import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class HomePresenter extends BaseDataFragmentPresenter<HomeFragment, Home> {

    private int mCurPage;

    @Override
    protected void onCreateView(HomeFragment view) {
        super.onCreateView(view);
        mCurPage = 1;
        loadData();
    }

    public void loadData() {
        ServiceClient.getServices().home(LUtils.getPreferences().getString("key", ""), mCurPage)
                .compose(new ServiceTransform<>())
                .subscribe(getSubscriber());
    }

    public void nextPage() {
        mCurPage += 1;
        ServiceClient.getServices().home(LUtils.getPreferences().getString("key", ""), mCurPage)
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<Home>() {
                    @Override
                    public void onNext(Home home) {
                        super.onNext(home);
                        getView().nextPage(home.getGuess_list());
                    }
                });
    }

}
