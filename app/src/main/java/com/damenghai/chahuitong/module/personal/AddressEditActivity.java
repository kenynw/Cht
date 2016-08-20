package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Address;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(AddressEditPresenter.class)
public class AddressEditActivity extends BaseDataActivity<AddressEditPresenter, Address> {

    @Bind(R.id.et_add_address_name)
    EditText mEtName;

    @Bind(R.id.et_add_address_mobile)
    EditText mEtMobile;

    @Bind(R.id.btn_add_address_area)
    Button mBtnArea;

    @Bind(R.id.et_add_address_address)
    EditText mEtAddress;

    @Bind(R.id.check_address_default)
    CheckBox mCheckDefault;

    @Bind(R.id.btn_add_address_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity_edit);
        setToolbarTitle(R.string.title_activity_add_address);
        ButterKnife.bind(this);

        mBtnArea.setOnClickListener(v -> getPresenter().showArea());
        mBtnSave.setOnClickListener(v -> getPresenter().save());
    }

    public void setAddress(Address address) {
        mEtName.setText(address.getTrue_name());
        mEtMobile.setText(address.getMob_phone());
        mEtAddress.setText(address.getAddress());
        mBtnArea.setText(address.getArea_info());
        mCheckDefault.setChecked(address.getIs_default() == 1);
    }

    public Address getAddress() {
        Address address = new Address();
        address.setTrue_name(mEtName.getText().toString());
        address.setMob_phone(mEtMobile.getText().toString());
        address.setArea_info(mBtnArea.getText().toString());
        address.setAddress(mEtAddress.getText().toString());
        address.setIs_default(mCheckDefault.isChecked() ? 1 : 0);
        return address;
    }

    public void setArea(String area) {
        mBtnArea.setText(area);
    }

}
