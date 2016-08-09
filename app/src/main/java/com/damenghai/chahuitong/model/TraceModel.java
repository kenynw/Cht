package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceModel {
    private static TraceModel mInstance;

    public static TraceModel getInstance() {
        if (null == mInstance) {
            synchronized (TraceModel.class) {
                if (null == mInstance) mInstance = new TraceModel();
            }
        }
        return mInstance;
    }

    public Observable<BeanList<Trace>> getFriendTraceList(int page) {
        return ServiceClient.getServices().friendTraceList(LUtils.getPreferences().getString("key", ""), page)
                .compose(new DefaultTransform<>());
    }

    public Observable<Trace> getTraceDetail(int traceId) {
        return ServiceClient.getServices().traceDetail(LUtils.getPreferences().getString("key", ""), traceId)
                .compose(new DefaultTransform<>());
    }
    public Observable<Integer> imageUpload(File[] files) {
        return Observable.from(files)
                .map(file -> {
                    RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    Map<String, RequestBody> photos = new HashMap<>();
                    photos.put("image\"; filename=\"" + file.getName() + "\"", photo);
                    photos.put("key", RequestBody.create(null, LUtils.getPreferences().getString("key", "")));
                    try {
                        Response<Integer> response = ServiceClient.getServices().traceUploadImage(photos).execute();
                        return response.body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> addTrace(String content, int imageId) {
        return ServiceClient.getServices().traceAdd(LUtils.getPreferences().getString("key", ""), content, imageId)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> delTrace(int traceId) {
        return ServiceClient.getServices().traceDel(LUtils.getPreferences().getString("key", ""), traceId)
                .compose(new DefaultTransform<>());
    }

    public Observable<TraceComment> addComment(int traceId, String content) {
        return ServiceClient.getServices().addTraceComment(LUtils.getPreferences().getString("key", ""), traceId, content)
                .compose(new DefaultTransform<>());
    }

    public Observable<TraceComment> replyComment(int id, String content) {
        return ServiceClient.getServices().replyTraceComment(LUtils.getPreferences().getString("key", ""), id, content)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> delComment(int id) {
        return ServiceClient.getServices().delTraceComment(LUtils.getPreferences().getString("key", ""), id)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> addTraceLike(int traceId) {
        return ServiceClient.getServices().addLike(LUtils.getPreferences().getString("key", ""), traceId, 0)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> addCommentLike(int commentId) {
        return ServiceClient.getServices().addLike(LUtils.getPreferences().getString("key", ""), commentId, 2)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Trace>> getUserTraceList(int mid, int curPage) {
        return ServiceClient.getServices().traceList(LUtils.getPreferences().getString("key", ""), mid, 0, curPage).compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Trace>> getTraceList(int mid, int curPage) {
        return ServiceClient.getServices().traceList("", mid, 0, curPage).compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Trace>> getCommendTraceList(int curPage) {
        return ServiceClient.getServices().traceList("", 0, 1, curPage).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> informTrace(int traceID, String content) {
        return ServiceClient.getServices().informTrace(LUtils.getPreferences().getString("key", ""), traceID, 0, content, 0)
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> informComment(int commentID, String content) {
        return ServiceClient.getServices().informTrace(LUtils.getPreferences().getString("key", ""), 0, commentID, content, 1)
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> informHome(int mid, String content) {
        return ServiceClient.getServices().informHome(LUtils.getPreferences().getString("key", ""), mid, content)
                .compose(new DefaultTransform<>());
    }

}
