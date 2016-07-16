package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(FleaCatePresenter.class)
public class FleaCateActivity extends BaseListActivity<FleaCatePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new CateViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.flea_activity_cate;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setLoadMoreAble(false).setNoMoreAble(false).setRefreshAble(false);
    }

}
