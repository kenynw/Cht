package com.damenghai.chahuitong.view.main;


import com.damenghai.chahuitong.model.bean.Banner;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.model.bean.Recommend;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface HomeMvpView extends MvpView {

    int getCurPage();

    void showBanner(List<Banner> banners);

    void showSpecial(Image sampleImage, Bargain goods);

    void showRecommend(List<Recommend> goodsList);

    void showRecommendForYou(List<Goods> goodsList);

}
