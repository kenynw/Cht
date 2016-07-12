package com.damenghai.chahuitong.module.flea;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.ImageUtils;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaAddPresenter extends BaseDataActivityPresenter<FleaAddActivity, Flea> {

    private Flea mFlea;

    @Override
    protected void onCreate(FleaAddActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mFlea = getView().getIntent().getParcelableExtra("flea");
        if (mFlea != null) {
            getView().setData(mFlea);
        }
    }

    @Override
    protected void onCreateView(FleaAddActivity view) {
        super.onCreateView(view);
        FleaModel.getInstance().getFleaDetail(mFlea != null ? mFlea.getGoods_id() : 0).subscribe(getDataSubscriber());
    }

    public void uploadImage(String path) {
        FleaModel.getInstance().uploadImage(path).subscribe(new ServiceResponse<FleaImage>(){
            @Override
            public void onNext(FleaImage fleaImage) {
                LUtils.toast(fleaImage.getFile_name());
            }
        });
    }

    public void saveFlea() {

    }

    public void updateFlea() {

    }

    public void shoPickImage() {
        uploadImage("");
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case  ImageUtils.GALLERY_REQUEST_CODE :
                    if (data == null) return;
                    Uri uri = data.getData();
                    String path = ImageUtils.getImageAbsolutePath19(getView(), uri);
                    LUtils.log("path: " + path);
                    uploadImage(path);
                    break;
                case ImageUtils.CAMERA_REQUEST_CODE :
                    ImageUtils.showZoomImage(getView());
                    break;
                case ImageUtils.ZOOM_REQUEST_CODE :
                    Bitmap bitmap = data.getParcelableExtra("data");
                    if (bitmap != null) {
                        LUtils.toast("zoom");
                    }
                    break;
            }
        } else {
            ImageUtils.deleteImageUri(getView());
        }
    }
}
