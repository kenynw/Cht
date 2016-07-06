package com.damenghai.chahuitong.module.goods;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.GoodsComment;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsCommentPresenter extends BaseListFragmentPresenter<GoodsCommentFragment, GoodsComment> {

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
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }
}
