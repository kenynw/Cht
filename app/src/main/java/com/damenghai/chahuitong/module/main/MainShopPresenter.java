package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.module.mall.CartListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainShopPresenter extends BaseDataFragmentPresenter<MainShopFragment, List<Category>> {

    @Override
    protected void onCreateView(MainShopFragment view) {
        super.onCreateView(view);
        GoodsModel.getInstance().getGoodsCategory(0).unsafeSubscribe(getSubscriber());
    }

    public void toCart() {
        if (checkLogin()) {
            Intent intent = new Intent(getView().getActivity(), CartListActivity.class);
            getView().startActivity(intent);
        }
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
