package com.damenghai.chahuitong.module.address;

import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(AreaPresenter.class)
public class AreaActivity extends BaseListActivity<AreaPresenter> {

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new AreaPresenter.AreaViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.address_activity_area;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setNoMoreAble(false);
    }

}
