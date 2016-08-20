package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaCate;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;
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

    public Observable<BeanList<Flea>> getFleaList(int page) {
        return ServiceClient.getServices().fleaList(LUtils.getPreferences().getString("key", ""), page)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Flea>> getMyFleaList() {
        return ServiceClient.getServices().myFleaList(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

    public Observable<Flea> getFleaDetail(int goodsId) {
        return ServiceClient.getServices().fleaDetail(LUtils.getPreferences().getString("key", ""), goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> uploadImage(List<FleaImage> fleaImages) {
        return Observable.from(fleaImages)
                .map(image -> {
                    if (image.getUri() != null) {
                        File file = new File(image.getUri().getPath());
                        RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        Map<String, RequestBody> photos = new HashMap<>();
                        photos.put("image\"; filename=\"" + file.getName() + "\"", photo);
                        photos.put("key", RequestBody.create(null, LUtils.getPreferences().getString("key", "")));
                        if (image.getUpload_id() > 0) photos.put("upload_id", RequestBody.create(null, image.getUpload_id() + ""));

                        try {
                            Response<FleaImage> response = ServiceClient.getServices().uploadFleaImage(photos).execute();
                            return response.body().getUpload_id();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    return 0;
                })
                .compose(new DefaultTransform<>());

    }

    public Observable<Integer> saveFlea(Flea flea, int imageId) {
        return ServiceClient.getServices().saveFlea(
                LUtils.getPreferences().getString("key", ""),
                flea.getGoods_name(), flea.getGoods_body(), imageId,
                flea.getGc_id(), flea.getGc_name(), flea.getGoods_store_price(),
                flea.getGoods_tag(), flea.getFlea_pname(), flea.getFlea_pphone(),
                flea.getFlea_area_id(), flea.getFlea_area_name()
        ).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> editFlea(int goodsID, Flea flea) {
        return ServiceClient.getServices().fleaEdit(
                LUtils.getPreferences().getString("key", ""),
                goodsID, flea.getGoods_name(), flea.getGc_id(), flea.getGc_name(),
                flea.getFlea_pname(), flea.getFlea_area_id(), flea.getFlea_area_name(), flea.getFlea_pphone(),
                flea.getGoods_tag(), flea.getGoods_store_price(), flea.getGoods_body()
        ).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> delFlea(int goodsId) {
        return ServiceClient.getServices().fleaDel(LUtils.getPreferences().getString("key", ""), goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> delFleaImage(int uploadId) {
        return ServiceClient.getServices().fleaImageDel(LUtils.getPreferences().getString("key", ""), uploadId)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Flea>> favorites(int curPage) {
        return ServiceClient.getServices().fleaFavorites(LUtils.getPreferences().getString("key", ""), curPage).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> addFavorite(int goodsId) {
        return ServiceClient.getServices().fleaAddFavorite(LUtils.getPreferences().getString("key", ""), goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> delFavorite(int goodsId) {
        return ServiceClient.getServices().fleaDelFavorite(LUtils.getPreferences().getString("key", ""), goodsId)
                .compose(new DefaultTransform<>());
    }

    public Observable<List<FleaCate>> cateList(int cateId) {
        return ServiceClient.getServices().fleaCateList(LUtils.getPreferences().getString("key", ""), cateId)
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
