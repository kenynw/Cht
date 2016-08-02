package com.damenghai.chahuitong.expansion.data;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.service.ServiceResponse;

import rx.Subscriber;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BaseDataActivityPresenter<V extends BaseDataActivity,M> extends Presenter<V> {
    //用于缓存数据的Subscriber
    BehaviorSubject<M>  mData = BehaviorSubject.create();
    //View的订阅关系，View被销毁时自动取消订阅。
    Subscription mSubscription;
    private Subscriber<M> mSubscriber = new ServiceResponse<M>() {

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mData.onError(e);
        }

        @Override
        public void onNext(M m) {
            super.onNext(m);
            mData.onNext(m);
        }
    };

    @Override
    protected void onCreateView(V view) {
        super.onCreateView(view);
        mSubscription = mData.subscribe(new Subscriber<M>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().onError(e);
            }

            @Override
            public void onNext(M m) {
                getView().setData(m);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    /**
     * 获取缓存数据的Subscriber
     * 可以通过 `getDataSubject().getValue();` 获取缓存数据。
     * @return
     */
    public BehaviorSubject<M> getDataSubject(){
        return mData;
    }


    /**
     * 获取存放数据的subscriber，通常用于
     *
     *     Observable.unsafeSubscribe(getDataSubscriber());
     *
     * @return
     */
    public Subscriber<M> getDataSubscriber(){
        return mSubscriber;
    }

    /**
     * 手动发布数据
     * @param data
     */
    public void publishObject(M data){
        mData.onNext(data);
    }

    /**
     * 手动发布错误
     * @param e
     */
    public void publishError(Throwable e){
        mData.onError(e);
    }

}
