package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Home implements Serializable {

    private ArrayList<Banner> adds;

    private String sample_image;

    private Bargain xianshi;

    private ArrayList<Goods> tasters_list;

    private ArrayList<Goods> guess_list;

    public ArrayList<Banner> getAdds() {
        return adds;
    }

    public void setAdds(ArrayList<Banner> adds) {
        this.adds = adds;
    }

    public String getSample_image() {
        return sample_image;
    }

    public void setSample_image(String sample_image) {
        this.sample_image = sample_image;
    }

    public Bargain getXianshi() {
        return xianshi;
    }

    public void setXianshi(Bargain xianshi) {
        this.xianshi = xianshi;
    }

    public ArrayList<Goods> getTasters_list() {
        return tasters_list;
    }

    public void setTasters_list(ArrayList<Goods> tasters_list) {
        this.tasters_list = tasters_list;
    }

    public ArrayList<Goods> getGuess_list() {
        return guess_list;
    }

    public void setGuess_list(ArrayList<Goods> guess_list) {
        this.guess_list = guess_list;
    }
}
