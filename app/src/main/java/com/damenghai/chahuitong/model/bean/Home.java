package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Home implements Serializable {
    private int isLogin;
    private ArrayList<Banner> carouselFigure;
    private Image goodsSample;
    private Bargain flashSale;
    private ArrayList<Recommend> tastersRecommends;
    private ArrayList<Goods> guessYouLike;

    public ArrayList<Goods> getGuessYouLike() {
        return guessYouLike;
    }

    public void setGuessYouLike(ArrayList<Goods> guessYouLike) {
        this.guessYouLike = guessYouLike;
    }

    public ArrayList<Recommend> getTastersRecommends() {
        return tastersRecommends;
    }

    public void setTastersRecommends(ArrayList<Recommend> tastersRecommends) {
        this.tastersRecommends = tastersRecommends;
    }

    public Image getGoodsSample() {
        return goodsSample;
    }

    public void setGoodsSample(Image goodsSample) {
        this.goodsSample = goodsSample;
    }

    public Bargain getFlashSale() {
        return flashSale;
    }

    public void setFlashSale(Bargain flashSale) {
        this.flashSale = flashSale;
    }

    public ArrayList<Banner> getCarouselFigure() {
        return carouselFigure;
    }

    public void setCarouselFigure(ArrayList<Banner> carouselFigure) {
        this.carouselFigure = carouselFigure;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }
}
