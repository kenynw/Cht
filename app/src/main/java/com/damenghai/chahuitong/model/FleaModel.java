package com.damenghai.chahuitong.model;

import android.os.Environment;

import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaModel {

    private static FleaModel mInstance;

    private FleaModel() {
    }

    public static FleaModel getInstance() {
        if (null == mInstance) {
            synchronized (FleaModel.class) {
                if (null == mInstance) mInstance = new FleaModel();
            }
        }
        return mInstance;
    }

    public Observable<BeanList<Flea>> getFleaList() {
        return ServiceClient.getServices().fleaList(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

    public Observable<Flea> getFleaDetail(int goodsId) {
        return ServiceClient.getServices().fleaDetail(LUtils.getPreferences().getString("key", ""), goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<FleaImage> uploadImage(String path) {
        File file = new File(path);
        RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);

//        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), photo);

        Map<String,RequestBody> photos = new HashMap<>();
        photos.put("image\"; filename=\"image.png\"", photo);
        photos.put("key", RequestBody.create(null, LUtils.getPreferences().getString("key", "")));

        return ServiceClient.getServices()
                .uploadFleaImage(photos)
                .compose(new DefaultTransform<>());
    }

    public Observable<Flea> fleaSave(int imageId, int cateId, String cateName, String pName, int areaId,
                                     String areaInfo, String pPhone, String price, String body) {
        return ServiceClient.getServices().fleaSave(LUtils.getPreferences().getString("key", ""), imageId, cateId,
                cateName, pName, areaId, areaInfo, pPhone, price, body)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Consult>> getConsultList(int page, int goodsId) {
        return ServiceClient.getServices().fleaConsultList(LUtils.getPreferences().getString("key", ""), page, goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Consult> saveConsult(String content, int goodsId) {
        return ServiceClient.getServices().addFleaConsult(LUtils.getPreferences().getString("key", ""), content, goodsId)
                .compose(new DefaultTransform<>());
    }

}
