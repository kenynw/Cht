package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddressModel {
    private static AddressModel mInstance;

    public static AddressModel getInstance() {
        if (null == mInstance) {
            synchronized (AddressModel.class) {
                if (null == mInstance) mInstance = new AddressModel();
            }
        }
        return mInstance;
    }

    private AddressModel() {}

    public Observable<BeanList<Address>> getAddressList() {
        return ServiceClient.getServices().addressList(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> addAddress(Address address) {
        return ServiceClient.getServices().addressAdd(LUtils.getPreferences().getString("key", ""),
                address.getTrue_name(), address.getMob_phone(), address.getCity_id(), address.getArea_id(),
                address.getArea_info(), address.getAddress(), address.getIs_default())
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> delAddress(int id) {
        return ServiceClient.getServices().addressDel(LUtils.getPreferences().getString("key", ""), id)
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> editAddress(int address_id, Address address) {
        return ServiceClient.getServices().addressEdit(LUtils.getPreferences().getString("key", ""),
                address_id, address.getTrue_name(), address.getMob_phone(),
                address.getCity_id(), address.getArea_id(), address.getArea_info(), address.getAddress(), address.getIs_default())
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> setDefault(int id) {
        return ServiceClient.getServices().addressSetDefault(LUtils.getPreferences().getString("key", ""), id)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Area>> getAreaList(int id) {
        return ServiceClient.getServices().areaList(LUtils.getPreferences().getString("key", ""), id)
                .compose(new DefaultTransform<>());
    }

}
