package com.damenghai.chahuitong.module.personal;

import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface FavoritesMvpView extends MvpView {

    // 删除收藏所需要参数
    List<String> getFavIdList();

    void showList(List<Goods> list);

    void showEmpty();

    void operateSuccess();

}
