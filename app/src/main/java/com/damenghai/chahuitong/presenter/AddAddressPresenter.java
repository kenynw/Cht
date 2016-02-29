package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.AddressRepository;
import com.damenghai.chahuitong.view.address.AddAddressMvpView;
import com.google.gson.JsonObject;

import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddAddressPresenter extends BasePresenter<AddAddressMvpView> {

    private AddressRepository mRepository;

    private PreferenceHelper mHelper;

    public AddAddressPresenter(Context context) {
        mRepository = mRetrofit.create(AddressRepository.class);
        mHelper = new PreferenceHelper(context);
    }

    public String getName() {
        return getView().getName();
    }

    public String getMobile() {
        return getView().getMobile();
    }

    public String getArea() {
        return getView().getArea();
    }

    public String getAreaId() {
        return getView().getAreaId();
    }

    public String getCityId() {
        return getView().getCityId();
    }

    public String getAddressInfo() {
        return getView().getAddressInfo();
    }

    public String getAddressId() {
        return getView().getAddressId();
    }

    public Address getAddress() {
        Address address = new Address();
        if (!TextUtils.isEmpty(getAddressId())) address.setAddress_id(getAddressId());
        address.setCity_id(getCityId());
        address.setArea_id(getAreaId());
        address.setTrue_name(getName());
        address.setMob_phone(getMobile());
        address.setArea_info(getArea());
        address.setAddress(getAddressInfo());
        return address;
    }

    public void save() {
        if (TextUtils.isEmpty(mHelper.readSession())) {
            getView().showError("请登录");
            return;
        }

        if(TextUtils.isEmpty(getName()) || TextUtils.isEmpty(getMobile())
                || TextUtils.isEmpty(getArea()) || TextUtils.isEmpty(getAddressInfo())) {
            getView().showError("请填写完整信息");
            return;
        }

        if(getName().length() < 2) {
            getView().showError("名字至少两个字符");
            return;
        }

        if(getMobile().length() != 11) {
            getView().showError("手机号码小于11位");
            return;
        }

        if(getArea().length() < 1) {
            getView().showError("请选择地区");
            return;
        }

        if(getAddressInfo().length() < 1) {
            getView().showError("请输入完整地址");
            return;
        }

        Subscriber<JsonObject> subscriber = new Subscriber<JsonObject>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onNext(JsonObject jsonObject) {
                if (jsonObject.getAsJsonObject("datas").has("error")) {
                    getView().showError(jsonObject.getAsJsonObject("datas").get("error").toString());
                } else {
                    EventBus.getDefault().post(getAddress());
                }
            }
        };

        if (TextUtils.isEmpty(getAddressId())) {
            mRepository.add(mHelper.readSession(), getName(), getMobile(), getCityId(),
                    getAreaId(), getAddressInfo(), getArea())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } else {
            mRepository.edit(mHelper.readSession(), mHelper.readSession(), getName(), getMobile(), getCityId(),
                    getAreaId(), getAddressInfo(), getArea())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

    }

}
