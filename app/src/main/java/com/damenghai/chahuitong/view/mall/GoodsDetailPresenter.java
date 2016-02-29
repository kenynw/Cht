package com.damenghai.chahuitong.view.mall;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.user.LoginActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsDetailPresenter extends BaseDataActivityPresenter<GoodsDetailActivity, GoodsInfo> {

    private String mGoodsId;

    private String mKey;

    @Override
    protected void onCreateView(GoodsDetailActivity view) {
        super.onCreateView(view);
        mGoodsId = getView().getIntent().getStringExtra("goods_id");
        showGoodsDetail();
    }

    private boolean isLogin() {
        if (TextUtils.isEmpty(mKey)) {
            mKey = LUtils.getPreferences().getString("key", "");
        }

        if (TextUtils.isEmpty(mKey)) {
            getView().startActivity(new Intent(getView(), LoginActivity.class));
            return false;
        }
        return true;
    }

    private void showGoodsDetail() {
        ServiceClient.getServices().goodsDetail(mGoodsId, mKey)
                .compose(new ServiceTransform<GoodsInfo>())
                .subscribe(getDataSubscriber());

    }

    public void addFavorites() {
        if (isLogin()) {
            ServiceClient.getServices().favAdd(mKey, mGoodsId, API.VERSION)
                    .compose(new ServiceTransform<String>())
                    .subscribe(new ServiceResponse<String>() {
                        @Override
                        public void onNext(String result) {
                            LUtils.toast("操作成功");
                        }
                    });
        }
    }

    public void addCart() {
        if (isLogin()) {
            ServiceClient.getServices().cartAdd(mKey, mGoodsId, "1")
                    .compose(new ServiceTransform<>())
                    .subscribe(new ServiceResponse<String>() {
                        @Override
                        public void onNext(String s) {
                            super.onNext(s);
                            DialogFactory.createCartDialog(getView());
                        }
                    });
        }
    }

    public void toCart() {
        if (isLogin()) {
            getView().startActivity(new Intent(getView(), CartActivity.class));
        }
    }

    public void toBuy() {
        if (isLogin()) {
            Intent intent = new Intent(getView(), BuyActivity.class);
            intent.putExtra("goods_id", mGoodsId);
            intent.putExtra("buynum", "1");
            getView().startActivity(intent);
        }
    }

}
