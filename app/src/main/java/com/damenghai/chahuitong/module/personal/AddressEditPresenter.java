package com.damenghai.chahuitong.module.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.AddressModel;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddressEditPresenter extends BaseDataActivityPresenter<AddressEditActivity, Address> {

    private final int REQUEST_CODE_CHOOSE_AREA = 0x001;

    private Address mAddress;

    private Area mArea;

    @Override
    protected void onCreate(AddressEditActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mAddress = getView().getIntent().getParcelableExtra("address");
    }

    @Override
    protected void onCreateView(AddressEditActivity view) {
        super.onCreateView(view);
        if (mAddress != null) {
            getView().setAddress(mAddress);
            mArea = new Area();
            mArea.setArea_id(mAddress.getArea_id());
            mArea.setArea_parent_id(mAddress.getCity_id());
        }
    }

    public void save() {
        Address address = getView().getAddress();
        if (TextUtils.isEmpty(address.getTrue_name()) || address.getTrue_name().length() < 2) {
            LUtils.toast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(address.getMob_phone()) || address.getMob_phone().length() != 11) {
            LUtils.toast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(address.getArea_info())) {
            LUtils.toast("请选择地区");
            return;
        }
        if (TextUtils.isEmpty(address.getAddress())) {
            LUtils.toast("请输入详细地址");
            return;
        }

        address.setArea_id(mArea.getArea_id());
        address.setCity_id(mArea.getArea_parent_id());

        ServiceResponse<Integer> response = new ServiceResponse<Integer>() {
            @Override
            public void onNext(Integer result) {
                EventBus.getDefault().post(result);
                getView().finish();
            }
        };

        if (mAddress != null && mAddress.getAddress_id() > 0) {
            AddressModel.getInstance().editAddress(mAddress.getAddress_id(), address).subscribe(response);
        } else {
            AddressModel.getInstance().addAddress(address).subscribe(response);
        }

    }

    public void showArea() {
        Intent intent = new Intent(getView(), AreaActivity.class);
        intent.putExtra("deep", 3);
        intent.putExtra("action", "com.cht.addressEdit");
        getView().startActivityForResult(intent, REQUEST_CODE_CHOOSE_AREA);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mArea = intent.getParcelableExtra("area");
        if (mArea != null) getView().setArea(mArea.getArea_name());
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CHOOSE_AREA && resultCode == Activity.RESULT_OK) {
            mArea = data.getParcelableExtra("area");
            getView().setArea(data.getStringExtra("area_info"));
        }
    }
}
