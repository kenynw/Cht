package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.bean.Message;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MessageListPresenter extends BaseListActivityPresenter<MessageListActivity, Message> {
    /**
     * 私信
     */
    public static final int TYPE_PRIVATE = 0;

    /**
     * 系统通知
     */
    public static final int TYPE_SYSTEM = 1;

    /**
     * 留言
     */
    public static final int TYPE_MESSAGE = 2;

    /**
     * 新的粉丝
     */
    public static final int TYPE_FANS = 3;

    /**
     * 评论和赞
     */
    public static final int TYPE_COMMENT = 4;

    /**
     * at我
     */
    public static final int TYPE_AT = 5;

    private int mType;

    @Override
    protected void onCreate(MessageListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mType = getView().getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void onCreateView(MessageListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().msgList(LUtils.getPreferences().getString("key", ""), mType, 1)
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().msgList(LUtils.getPreferences().getString("key", ""), mType, getCurPage())
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getMoreSubscriber());
    }

}
