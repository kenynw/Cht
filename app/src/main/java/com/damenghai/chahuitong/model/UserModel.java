package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.People;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserModel {

    private static UserModel mInstance;

    public static UserModel getInstance() {
        if (null == mInstance) {
            synchronized (UserModel.class) {
                if (null == mInstance) mInstance = new UserModel();
            }
        }
        return mInstance;
    }

    public Observable<User> getUserInfo() {
        return ServiceClient.getServices().getUser(API.VERSION, LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

    /**
     * 我的资料
     * @return 我的页面
     */
    public Observable<User> getProfile() {
        return ServiceClient.getServices().profile(LUtils.getPreferences().getString("key", "")).compose(new DefaultTransform<>());
    }

    /**
     * 个人主页
     * @param userID 用户ID
     * @return 个人主页
     */
    public Observable<People> userHome(int userID) {
        return ServiceClient.getServices().userHome(LUtils.getPreferences().getString("key", ""), userID).compose(new DefaultTransform<>());
    }

    /**
     * 获取用户关注列表
     * @param userID 用户ID
     * @return 用户列表
     */
    public Observable<BeanList<User>> getFollowList(int curPage, int userID, int type) {
        return ServiceClient.getServices().followList(LUtils.getPreferences().getString("key", ""), curPage, userID, type)
                .compose(new DefaultTransform<>());
    }

    /**
     * 上传头像
     * @param file 头像文件
     * @return 上传结果
     */
    public Observable<Boolean> uploadAvatar(File file) {
        RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Map<String, RequestBody> photos = new HashMap<>();
        photos.put("image\"; filename=\"" + file.getName() + "\"", photo);
        photos.put("key", RequestBody.create(null, LUtils.getPreferences().getString("key", "")));

        return ServiceClient.getServices().uploadAvatar(photos).compose(new DefaultTransform<>());
    }

    /**
     * 更新个人资料
     * @param user 用户
     * @return 结果
     */
    public Observable<Boolean> updateProfile(User user) {
        return ServiceClient.getServices().updateUserInfo(user, LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

}
