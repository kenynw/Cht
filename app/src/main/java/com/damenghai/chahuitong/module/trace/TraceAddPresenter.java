package com.damenghai.chahuitong.module.trace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.damenghai.chahuitong.app.App;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.exgridview.PieceViewGroup.OnViewDeleteListener;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action0;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceAddPresenter extends Presenter<TraceAddActivity> implements OnImageSelectListener, OnViewDeleteListener {

    private List<Uri> mUriList;

    private ImageProvider mProvider;

    @Override
    protected void onCreate(TraceAddActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUriList = new ArrayList<>();
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(TraceAddActivity view) {
        super.onCreateView(view);
    }

    public void save(String content) {
        if (mUriList.size() <= 0) {
            LUtils.toast("无图无真相");
            return;
        }

        getView().getExpansionDelegate().showProgressBar();

        File[] files = new File[mUriList.size()];
        for (int i=0; i<mUriList.size(); i++) {
            files[i] = new File(mUriList.get(i).getPath());
        }

        TraceModel.getInstance().imageUpload(files)
                .doOnError(throwable -> getView().getExpansionDelegate().hideProgressBar())
                .toList()
                .flatMap(id_list -> TraceModel.getInstance().addTrace(content, id_list.get(0)))
                .finallyDo(() -> getView().getExpansionDelegate().hideProgressBar())
                .subscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        getView().finish();
                    }
                });
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

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        mProvider.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewDelete(int index) {
        mUriList.remove(index);
    }

    @Override
    public void onImageSelect() {
        getView().getExpansionDelegate().showProgressBar();
    }

    @Override
    public void onImageLoaded(Uri uri) {
        getView().getExpansionDelegate().hideProgressBar();
        getView().addImage(ImageProvider.readImageWithSize(uri, 300, 300));

        mUriList.add(uri);
    }

    @Override
    public void onError() {
    }

}
