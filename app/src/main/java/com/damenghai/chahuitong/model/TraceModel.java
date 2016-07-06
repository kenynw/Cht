package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

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

    public Observable<BeanList<Trace>> getTraceCommendList() {
        return ServiceClient.getServices().traceList(LUtils.getPreferences().getString("key", ""), 1)
                .compose(new DefaultTransform<>());
    }

    public Observable<Trace> getTraceDetail(int traceId) {
        return ServiceClient.getServices().traceDetail(LUtils.getPreferences().getString("key", ""), traceId)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> delTrace(int traceId) {
        return ServiceClient.getServices().traceDel(LUtils.getPreferences().getString("key", ""), traceId)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> addComment(int traceId, String content) {
        return ServiceClient.getServices().addTraceComment(LUtils.getPreferences().getString("key", ""), traceId, content)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> delComment(int traceId) {
        return ServiceClient.getServices().delTraceComment(LUtils.getPreferences().getString("key", ""), traceId)
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

    public Observable<BeanList<Trace>> getTraceList(int curPage) {
        return ServiceClient.getServices().traceList(LUtils.getPreferences().getString("key", ""), curPage)
                .compose(new DefaultTransform<>());
    }

}
