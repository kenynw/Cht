package com.damenghai.chahuitong.model;

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
public class MemberModel {

    private static MemberModel mInstance;

    public static MemberModel getInstance() {
        if (null == mInstance) {
            synchronized (MemberModel.class) {
                if (null == mInstance) mInstance = new MemberModel();
            }
        }
        return mInstance;
    }

    public Observable<String> logout() {
        return ServiceClient.getServices().logout(LUtils.getPreferences().getString("mobile", ""), "android").compose(new DefaultTransform<>());
    }

    public Observable<User> getMemberInfo() {
        return ServiceClient.getServices().getUserInfo(LUtils.getPreferences().getString("key", "")).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> uploadAvatar(File file) {
        RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Map<String, RequestBody> photos = new HashMap<>();
        photos.put("image\"; filename=\"" + file.getName() + "\"", photo);
        photos.put("key", RequestBody.create(null, LUtils.getPreferences().getString("key", "")));

        return ServiceClient.getServices().uploadAvatar(photos).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> updateProfile(User user) {
        return ServiceClient.getServices().updateUserInfo(user, LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

}
