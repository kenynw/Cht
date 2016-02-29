package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Comment implements Serializable {
    private String geval_scores;

    private String geval_content;

    private String geval_isanonymous;

    private String geval_frommembername;

    public String getGeval_scores() {
        return geval_scores;
    }

    public void setGeval_scores(String geval_scores) {
        this.geval_scores = geval_scores;
    }

    public String getGeval_content() {
        return geval_content;
    }

    public void setGeval_content(String geval_content) {
        this.geval_content = geval_content;
    }

    public String getGeval_isanonymous() {
        return geval_isanonymous;
    }

    public void setGeval_isanonymous(String geval_isanonymous) {
        this.geval_isanonymous = geval_isanonymous;
    }

    public String getGeval_frommembername() {
        return geval_frommembername;
    }

    public void setGeval_frommembername(String geval_frommembername) {
        this.geval_frommembername = geval_frommembername;
    }
}
