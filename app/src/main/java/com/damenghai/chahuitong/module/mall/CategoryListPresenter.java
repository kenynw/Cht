package com.damenghai.chahuitong.module.mall;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.service.ServiceResponse;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CategoryListPresenter extends BaseListActivityPresenter<CategoryListActivity, Category> {

    private int mGcId;

    @Override
    protected void onCreate(CategoryListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mGcId = getView().getIntent().getIntExtra("gc_id", 0);
    }

    @Override
    protected void onCreateView(CategoryListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        GoodsModel.getInstance().getGoodsCategory(mGcId)
                .unsafeSubscribe(new ServiceResponse<List<Category>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().stopRefresh();
                        getView().showError();
                    }

                    @Override
                    public void onNext(List<Category> list) {
                        getAdapter().clear();
                        getAdapter().addAll(list);
                    }
                });
    }

}
