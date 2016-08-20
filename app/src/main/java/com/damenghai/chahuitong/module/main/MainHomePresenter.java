package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.bean.SpecialItem;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.module.mall.CartListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainHomePresenter extends Presenter<MainHomeFragment> implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreateView(MainHomeFragment view) {
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

    public void showCart() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), CartListActivity.class);
            getView().startActivity(i);
        }
    }

    public void showGoodsList(String op) {
        Intent intent = new Intent(getView().getActivity(), GoodsListActivity.class);
        intent.putExtra("op", op);
        getView().startActivity(intent);
    }

    public void itemClick(SpecialItem item) {
        Intent intent = new Intent();
        switch (item.getType()) {
            case "url" :
                if (item.getData().startsWith("http://") || item.getData().startsWith("https://")) {
                    intent.setClass(getView().getActivity(), WebViewActivity.class);
                    intent.putExtra("url", item.getData());
                } else {
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(item.getData()));
                }
                break;
            case "keyword" :
                intent.setClass(getView().getActivity(), GoodsListActivity.class);
                intent.putExtra("keyword", item.getData());
                break;
            case "special" :

                break;
            case "goods" :
                intent.setClass(getView().getActivity(), GoodsDetailActivity.class);
                intent.putExtra("goods_id", Integer.valueOf(item.getData()));
                break;
            default :

                break;
        }
        getView().startActivity(intent);
    }

    private boolean checkLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            Intent i = new Intent(getView().getActivity(), LoginActivity.class);
            getView().startActivity(i);
            return false;
        } else {
            return true;
        }
    }

}
