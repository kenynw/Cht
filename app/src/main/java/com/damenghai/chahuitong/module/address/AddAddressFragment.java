package com.damenghai.chahuitong.module.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.model.bean.Address;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddAddressFragment extends BaseFragment implements AddAddressMvpView {
    private final String KEY_ADDRESS = "AddressFragment:Address";
    private final int REQUEST_CODE_CHOOSE_AREA = 0x001;

    @Bind(R.id.et_add_address_name)
    EditText mEtName;

    @Bind(R.id.et_add_address_mobile)
    EditText mEtMobile;

    @Bind(R.id.tv_add_address_area)
    TextView mTvArea;

    @Bind(R.id.et_add_address_address)
    EditText mEtAddress;

    private String mCityId;

    private String mAreaId;

    private String mAreaInfo;

    private Address mAddress;

    private AddAddressPresenter mPresenter;

    public static AddAddressFragment get(Address address) {
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.mAddress = address;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_ADDRESS)) {
            mAddress = (Address) savedInstanceState.get(KEY_ADDRESS);
        }
        mPresenter = new AddAddressPresenter(mContext);
        mPresenter.attach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_add_address, container, false);
        ButterKnife.bind(this, view);

        if(mAddress != null) {
            mEtName.setText(mAddress.getTrue_name());
            mEtMobile.setText(mAddress.getMob_phone());
            mTvArea.setText(mAddress.getArea_info());
            mEtAddress.setText(mAddress.getAddress());
            mCityId = mAddress.getCity_id();
            mAreaId = mAddress.getArea_id();
            mAreaInfo = mAddress.getArea_info();
        }

        return view;
    }

    @OnClick(R.id.tv_add_address_area)
    public void addAddress() {
        Intent intent = new Intent(mContext, AreaActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_AREA);
    }

    @OnClick(R.id.add_address_btn_save)
    public void save() {
        mPresenter.save();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

    @Override
    public String getName() {
        return mEtName.getText().toString();
    }

    @Override
    public String getMobile() {
        return mEtMobile.getText().toString();
    }

    @Override
    public String getArea() {
        return mTvArea.getText().toString();
    }

    @Override
    public String getAddressId() {
        return mAddress == null ? "" : mAddress.getAddress_id();
    }

    @Override
    public String getAddressInfo() {
        return mEtAddress.getText().toString();
    }

    @Override
    public String getCityId() {
        return mCityId;
    }

    @Override
    public String getAreaId() {
        return mAreaId;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ADDRESS, mAddress);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_CHOOSE_AREA && resultCode == Activity.RESULT_OK) {
            mCityId = data.getStringExtra("cityId");
            mAreaId = data.getStringExtra("areaId");
            mAreaInfo = data.getStringExtra("area");
            mTvArea.setText(mAreaInfo);
        }
    }

}
