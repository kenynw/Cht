package com.damenghai.chahuitong.model.throwable;

import com.damenghai.chahuitong.utils.L;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ResponseThrowable extends Throwable{
    public ResponseThrowable() {
    }

    public ResponseThrowable(String detailMessage) {
        super(detailMessage);
    }

    public ResponseThrowable(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }

    public ResponseThrowable(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        if (getMessage().contains("UnknownHostException")) {
            return "网络错误";
        }

        return super.getLocalizedMessage();
    }

}
