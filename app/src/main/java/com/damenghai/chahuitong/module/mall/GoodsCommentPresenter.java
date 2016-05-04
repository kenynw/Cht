package com.damenghai.chahuitong.module.mall;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Comment;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsCommentPresenter extends BaseListFragmentPresenter<GoodsCommentFragment, Comment> {

    private String mGoodsId;

    @Override
    protected void onCreateView(GoodsCommentFragment view) {
        super.onCreateView(view);
        mGoodsId = getView().getArguments().getString("goodsId");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().goodsComments(mGoodsId)
                .compose(new ServiceTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }
}
