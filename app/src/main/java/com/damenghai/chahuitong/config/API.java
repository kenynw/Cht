package com.damenghai.chahuitong.config;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface API {
    String VERSION = "3.0";

    /**
     * 获取数据成功
     */
    int SUCCESS = 1;

    /**
     * 获取数据失败，具体描述请查看msg字段
     */
    int CODE_DATA_INVALID = 0;

    /**
     *  用户没有登录
     */
    int CODE_LOGIN_INVALID = -1;

    /**
     * 网络不可用
     */
    int CODE_NET_INVALID = -2;

    // 其他...
}
