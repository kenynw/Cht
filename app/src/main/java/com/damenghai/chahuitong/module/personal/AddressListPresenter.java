package com.damenghai.chahuitong.module.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.AddressViewHolder;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.AddressModel;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AddressListPresenter extends BaseListActivityPresenter<AddressListActivity, Address> implements AddressViewHolder.OnOperationListener {

    private int mState;

    @Override
    protected void onCreate(AddressListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mState = getView().getIntent().getIntExtra("state", 0);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(AddressListActivity view) {
        super.onCreateView(view);
        onRefresh();
        getAdapter().setOnItemClickListener(position -> {
            if (mState == 1) {
                Intent intent = new Intent().putExtra("address", getAdapter().getItem(position));
                getView().setResult(Activity.RESULT_OK, intent);
                getView().finish();
            } else {
                Intent intent = new Intent(getView(), AddressEditActivity.class);
                intent.putExtra("address", getAdapter().getItem(position));
                getView().startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        AddressModel.getInstance().getAddressList().unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onDelete(Address address) {
        AddressModel.getInstance().delAddress(address.getAddress_id())
                .subscribe(new ServiceResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        LUtils.toast(R.string.toast_del_success);
                        getAdapter().remove(address);
                    }
                });
    }

    @Override
    public void onSetDefault(Address address) {
        AddressModel.getInstance().setDefault(address.getAddress_id())
                .subscribe(new ServiceResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        onRefresh();
                    }
                });
    }

    public void showEditAddress() {
        Intent intent = new Intent(getView(), AddressEditActivity.class);
        getView().startActivityForResult(intent, 0);
    }

    public void onEvent(Integer result) {
        onRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
