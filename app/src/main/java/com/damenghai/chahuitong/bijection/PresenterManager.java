package com.damenghai.chahuitong.bijection;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PresenterManager {
    private static PresenterManager mInstance;

    Map<String, Presenter> mPresenterMap = new HashMap<>();

    private int mNextId;

    public static PresenterManager getInstance() {
        if (mInstance == null) {
            mInstance = new PresenterManager();
        }
        return mInstance;
    }

    public  <PresenterType extends Presenter> PresenterType create(Object view) {
        PresenterType presenter = PresenterBuilder.fromViewClass(view.getClass());
        presenter.mId = provideId();
        mPresenterMap.put(presenter.mId, presenter);
        return presenter;
    }

    public <PresenterType extends Presenter> PresenterType get(String id) {
        return (PresenterType) mPresenterMap.get(id);
    }

    public void destroy(String id) {
        mPresenterMap.remove(id);
    }

    private String provideId() {
        return mNextId++ + "/" + System.nanoTime() + "/" + (int) (Math.random() * Integer.MAX_VALUE);
    }

}
