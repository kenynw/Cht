package com.damenghai.chahuitong.view.address;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface AddressListMvpView extends MvpView {

    void showList(List<Address> list);

    List<String> getSelectedItems();

}
