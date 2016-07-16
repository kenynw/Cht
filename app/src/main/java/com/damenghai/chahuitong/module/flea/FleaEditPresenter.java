package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;

import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaEditPresenter extends FleaAddPresenter {

    private Flea mFlea;

    @Override
    protected void onCreate(FleaAddActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mFlea = getView().getIntent().getParcelableExtra("flea");
    }

    @Override
    protected void onCreateView(FleaAddActivity view) {
        super.onCreateView(view);
        FleaModel.getInstance().getFleaDetail(mFlea.getGoods_id()).subscribe(getDataSubscriber());
    }

    public void saveFlea(Flea flea) {

    }

    @Override
    public void onViewDelete(int index) {
//        if (mFlea.getDesc_image() != null && mFlea.getDesc_image().size() > 0) {
//            if (index < mFlea.getDesc_image().size()) {
//                FleaImage image = mFlea.getDesc_image().get(index);
//                FleaModel.getInstance().delFleaImage(image.getUpload_id());
//            } else {
//                mUriList.remove(index - mFlea.getDesc_image().size());
//            }
//        } else {
//            mUriList.remove(index);
//        }
    }

}
