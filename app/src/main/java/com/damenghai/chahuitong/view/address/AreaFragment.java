package com.damenghai.chahuitong.view.address;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.presenter.AreaPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AreaFragment extends BaseListFragment implements AreaMvpView {
    private final String KEY_AREA_ID = "AreaFragment:id";

    private AreaPresenter mPresenter;

    private String mAreaId;

    public static AreaFragment get(String areaId) {
        AreaFragment fragment = new AreaFragment();
        fragment.mAreaId = areaId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_AREA_ID)) {
            mAreaId = savedInstanceState.getString(KEY_AREA_ID);
        }
    }

    @Override
    public void loadData() {
        mPresenter.showAreaList();
    }

    @Override
    public String getAreaId() {
        return mAreaId;
    }

    @Override
    public void setAreaList(List<Area> list) {
        getRecycleView().setAdapter(new AreaAdapter(mContext, list));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new AreaPresenter(mContext);
        mPresenter.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_AREA_ID, mAreaId);
    }

    private class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {

        private Context mContext;

        private List<Area> mList;

        public AreaAdapter(Context context, List<Area> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public AreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            return new AreaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AreaViewHolder holder, int position) {
            final Area area = mList.get(position);
            holder.tv.setText(area.getArea_name());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(area);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class AreaViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public AreaViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

    }

}
