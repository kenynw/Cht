package com.damenghai.chahuitong.module.flea;

import android.content.Intent;
import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.FleaCate;
import com.damenghai.chahuitong.model.service.ServiceResponse;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaCatePresenter extends BaseListActivityPresenter<FleaCateActivity, FleaCate> {

    private FleaCate mCate;

    @Override
    protected void onCreate(FleaCateActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mCate = getView().getIntent().getParcelableExtra("cate");
    }

    @Override
    protected void onCreateView(FleaCateActivity view) {
        super.onCreateView(view);
        onRefresh();
        getAdapter().setOnItemClickListener(position -> {
            FleaCate cate = getAdapter().getItem(position);
            if (cate.has_child()) {
                Intent intent = new Intent(getView(), FleaCateActivity.class);
                intent.putExtra("cate", cate);
                getView().startActivity(intent);
            } else {
                if (mCate != null) cate.setGc_name(mCate.getGc_name().trim() + ">" + cate.getGc_name().trim());
                Intent intent = new Intent(getView(), FleaAddActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("cate", cate);
                getView().startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        FleaModel.getInstance().cateList(mCate == null ? 0 : mCate.getGc_id()).unsafeSubscribe(new ServiceResponse<List<FleaCate>>(){
            @Override
            public void onNext(List<FleaCate> fleaCates) {
                getAdapter().clear();
                getAdapter().addAll(fleaCates);
            }
        });
    }

}
