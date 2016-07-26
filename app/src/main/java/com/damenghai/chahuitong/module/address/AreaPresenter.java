package com.damenghai.chahuitong.module.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.AddressModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.module.personal.ProfileActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AreaPresenter extends BaseListActivityPresenter<AreaActivity, Area> {

    private int mDeep;

    private Area mArea;

    @Override
    protected void onCreate(AreaActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mDeep = getView().getIntent().getIntExtra("deep", 0);
        mArea = getView().getIntent().getParcelableExtra("area");
    }

    @Override
    protected void onCreateView(AreaActivity view) {
        super.onCreateView(view);
        onRefresh();
        if (mArea != null) getView().setToolbarTitle(mArea.getArea_name());
        else getView().setToolbarTitle(R.string.title_activity_province);

        getAdapter().setOnItemClickListener(position -> {
            Area area = getAdapter().getItem(position);
            if (mArea != null) area.setArea_name(mArea.getArea_name() + " " + area.getArea_name());
            Intent intent = new Intent();
            intent.putExtra("area", area);
            if (area.getArea_deep() == (mDeep > 0 ? mDeep : 3)) {
                intent.setClass(getView(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            } else {
                intent.putExtra("deep", mDeep);
                intent.setClass(getView(), AreaActivity.class);
            }
            getView().startActivity(intent);
        });
    }

    @Override
    public void onRefresh() {
        AddressModel.getInstance().getAreaList(mArea != null ? mArea.getArea_id() : 0)
                .unsafeSubscribe(getRefreshSubscriber());
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
        }
    }

}
