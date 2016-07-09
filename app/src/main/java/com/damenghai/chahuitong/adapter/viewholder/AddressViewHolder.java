package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.module.address.AddressEditActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddressViewHolder extends BaseViewHolder<Address> {

    @Bind(R.id.tv_address_name)
    TextView mTvName;

    @Bind(R.id.tv_address_mobile)
    TextView mTvMobile;

    @Bind(R.id.tv_address_detail)
    TextView mTvDetail;

    @Bind(R.id.check_address_default)
    CheckBox mCheckEdit;

    @Bind(R.id.btn_address_del)
    Button mBtnDel;

    @Bind(R.id.btn_address_edit)
    Button mBtnEdit;

    private OnOperationListener mListener;

    public AddressViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_address);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Address data) {
        mTvName.setText(data.getTrue_name());
        mTvMobile.setText(data.getMob_phone());
        mTvDetail.setText(String.format("%1$s %2$s", data.getArea_info(), data.getAddress()));
        mCheckEdit.setChecked(data.getIs_default() == 1);
        mCheckEdit.setOnCheckedChangeListener((button, isChecked) -> {
            if (isChecked && mListener != null) mListener.onSetDefault(data);
        });
        mBtnDel.setOnClickListener(v -> DialogFactory.createGenericDialog(getContext(), R.string.dialog_delete_address,
                (dialog, which) -> {
                    if (mListener != null) mListener.onDelete(data);
                }).show());
        mBtnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddressEditActivity.class);
            intent.putExtra("address", data);
            getContext().startActivity(intent);
        });

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddressEditActivity.class);
            intent.putExtra("address", data);
            getContext().startActivity(intent);
        });
    }

    public interface OnOperationListener {
        void onDelete(Address address);
        void onSetDefault(Address address);
    }

    public void setOnOperationListener(OnOperationListener listener) {
        mListener = listener;
    }

}
