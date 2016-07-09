package com.damenghai.chahuitong.module.address;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.AddressModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.AddressRepository;
import com.damenghai.chahuitong.presenter.BasePresenter;
import com.damenghai.chahuitong.utils.L;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void showAreaList() {
//        mRepository.areaList(getToken(), getView().getAreaId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Response<ListResponse>>() {
//
//                    @Override
//                    public void onStart() {
//                        getView().showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        getView().hideLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getView().showError(e.getMessage());
//                        L.d(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Response<ListResponse> jsonObject) {
//                        ListResponse response = jsonObject.getDatas();
//                        if (response.isError()) {
//                            getView().showError(response.getError());
//                        } else {
//                            getView().setAreaList(response.getArea_list());
//                        }
//                    }
//                });
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
