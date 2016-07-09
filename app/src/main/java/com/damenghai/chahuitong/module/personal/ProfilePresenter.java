package com.damenghai.chahuitong.module.personal;

import android.text.TextUtils;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ProfilePresenter extends BaseDataActivityPresenter<ProfileActivity, User> {

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        showUserInfo();
    }

    public void showUserInfo() {
        ServiceClient.getServices().getUserInfo(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(getDataSubscriber());
    }

    public void updateUserInfo() {
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), "key");
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), getView().getTrueName());
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), getView().getGender());
        RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), getView().getBirthday());

        Map<String, RequestBody> map = new HashMap<>();
        map.put("key", key);
        map.put("member_truename",name);
        map.put("member_sex",gender);
        map.put("member_birthday",birthday);

        if (!TextUtils.isEmpty(getView().getAvatarBase64())) {
            RequestBody avatar = RequestBody.create(MediaType.parse("image/*"), getView().getAvatarBase64());
            map.put("member_avatar", avatar);
        }

//        mRepository.updateUserInfo(map)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//                    LUtils.toast(R.string.toast_operate_success);
//                    ((Activity) getView()).finish();
//                });
    }

}
