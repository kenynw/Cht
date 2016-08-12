package com.damenghai.chahuitong.module.main;

import android.content.Intent;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SearchPresenter extends BaseDataActivityPresenter<SearchActivity, String[]> {

    @Override
    protected void onCreateView(SearchActivity view) {
        super.onCreateView(view);
        GoodsModel.getInstance().getHotSearch().subscribe(getDataSubscriber());
    }

    public void showGoodsList(String keyword) {
        if (checkLogin()) {
            Intent i = new Intent(getView(), GoodsListActivity.class);
            i.putExtra("op", "goods_list");
            i.putExtra("keyword", keyword);
            getView().startActivity(i);
        }
    }

    private boolean checkLogin() {
        if (LUtils.getPreferences().getString("key", "").isEmpty()) {
            Intent i = new Intent(getView(), LoginActivity.class);
            getView().startActivity(i);
            return false;
        }
        return true;
    }

}
