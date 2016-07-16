package com.damenghai.chahuitong.module.address;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.AddressModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AreaPresenter extends BaseListFragmentPresenter<AreaFragment, Area> {

    private int mAreaId = 0;

    public static AreaFragment newFragment(int areaId) {
        AreaFragment fragment = new AreaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("area_id", areaId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreate(AreaFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) mAreaId = getView().getArguments().getInt("area_id");
    }

    @Override
    protected void onCreateView(AreaFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        AddressModel.getInstance().getAreaList(mAreaId).unsafeSubscribe(getRefreshSubscriber());
    }

    public static class AreaViewHolder extends BaseViewHolder<Area> {

        @Bind(android.R.id.text1)
        TextView mTvText;

        public AreaViewHolder(ViewGroup parent) {
            super(parent, android.R.layout.simple_expandable_list_item_1);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Area data) {
            mTvText.setText(data.getArea_name());
            itemView.setOnClickListener(v -> EventBus.getDefault().post(data));
        }
    }

}
