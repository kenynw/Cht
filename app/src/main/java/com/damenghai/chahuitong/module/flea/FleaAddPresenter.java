package com.damenghai.chahuitong.module.flea;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaCate;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.address.AreaActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.exgridview.PieceViewGroup.OnViewDeleteListener;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaAddPresenter extends BaseDataActivityPresenter<FleaAddActivity, Flea>
        implements OnViewDeleteListener, OnImageSelectListener {

    private static final int REQUEST_AREA = 0;

    private ImageProvider mProvider;

    private ArrayList<FleaImage> mUriList = new ArrayList<>();

    private Flea mFlea;

    private boolean mIsQuotation;

    private FleaCate mCate;

    private Area mArea;

    private String mAreaInfo;

    @Override
    protected void onCreate(FleaAddActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProvider = new ImageProvider(getView());
        mFlea = getView().getIntent().getParcelableExtra("flea");
    }

    @Override
    protected void onCreateView(FleaAddActivity view) {
        super.onCreateView(view);
        FleaModel.getInstance().getFleaDetail(mFlea != null ? mFlea.getGoods_id() : 0).subscribe(getDataSubscriber());
    }

    public void saveFlea(Flea flea) {
        getView().getExpansionDelegate().showProgressBar();

        if (mIsQuotation) flea.setGoods_store_price(0);
        flea.setFlea_area_id(mArea == null ? 0 : mArea.getArea_id());
        flea.setGc_id(mCate != null ? mCate.getGc_id() : 0);
        flea.setGc_name(mCate != null ? mCate.getGc_name().replace(">", " ") : "");

        if (mFlea != null) {
            FleaModel.getInstance().editFlea(mFlea.getGoods_id(), flea, mAreaInfo)
                    .subscribe(new ServiceResponse<Boolean>() {
                        @Override
                        public void onNext(Boolean result) {
                            getView().finish();
                        }
                    });
        } else if (mUriList.size() > 0) {
            FleaModel.getInstance().uploadImage(mUriList)
                    .doOnError(throwable -> {
                        getView().getExpansionDelegate().hideProgressBar();
                        LUtils.toast("图片上传失败");
                    })
                    .toList()
                    .flatMap(uploadId -> FleaModel.getInstance().saveFlea(flea, uploadId.get(0), mAreaInfo))
                    .finallyDo(() -> getView().getExpansionDelegate().hideProgressBar())
                    .subscribe(new ServiceResponse<Integer>() {
                        @Override
                        public void onNext(Integer integer) {
                            Intent i = new Intent(getView(), MyFleaActivity.class);
                            getView().startActivity(i);
                            getView().finish();
                        }
                    });

        } else {
            FleaModel.getInstance().saveFlea(flea, 0, mAreaInfo)
                    .subscribe(new ServiceResponse<Integer>() {
                        @Override
                        public void onNext(Integer integer) {
                            Intent i = new Intent(getView(), MyFleaActivity.class);
                            getView().startActivity(i);
                            getView().finish();
                        }
                    });
        }

    }

    public void showArea() {
        Intent intent = new Intent(getView(), AreaActivity.class);
        getView().startActivityForResult(intent, REQUEST_AREA);
    }

    public void showCate() {
        Intent intent = new Intent(getView(), FleaCateActivity.class);
        getView().startActivity(intent);
    }

    public void addImage(FleaImage image) {
        mUriList.add(image);
    }

    public void editFace(int style) {
        if (mUriList.size() >= 8) {
            LUtils.toast("最多上传8张图片");
            return;
        }
        switch (style) {
            case 0:
                mProvider.getImageFromCamera(this);
                break;
            case 1:
                mProvider.getImageFromAlbum(this, 8 - mUriList.size());
                break;
        }
    }

    public void setIsQuotation(boolean isQuotation) {
        mIsQuotation = isQuotation;
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        mProvider.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_AREA:
                    mArea = data.getParcelableExtra("area");
                    mAreaInfo = data.getStringExtra("area_info");
                    getView().setArea(mAreaInfo);
                    break;
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mCate = intent.getParcelableExtra("cate");
        getView().setCate(mCate.getGc_name());
    }

    @Override
    public void onViewDelete(int index) {
        if (mFlea != null && mFlea.getDesc_image() != null && mFlea.getDesc_image().size() > 0) {
            if (index < mFlea.getDesc_image().size()) {
                FleaImage image = mFlea.getDesc_image().get(index);
                FleaModel.getInstance().delFleaImage(image.getUpload_id());
            } else {
                mUriList.remove(index - mFlea.getDesc_image().size());
            }
        } else {
//        Uri uri = mUriList.get(index);
//        if ("http".equals(uri.getScheme())) {
//            FleaModel.getInstance().delFleaImage()
//        } else {
            mUriList.remove(index);
//        }
        }
    }

    @Override
    public void onImageSelect() {
        getView().getExpansionDelegate().showProgressBar();
    }

    @Override
    public void onImageLoaded(Uri uri) {
        getView().getExpansionDelegate().hideProgressBar();
        getView().addImage(ImageProvider.readImageWithSize(uri, 300, 300));

        FleaImage image = new FleaImage();
        image.setUri(uri);
        mUriList.add(image);
    }

    @Override
    public void onError() {

    }

}
