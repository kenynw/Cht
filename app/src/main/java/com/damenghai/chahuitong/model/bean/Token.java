package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Token implements Serializable {
    private String key;

    public Token(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
