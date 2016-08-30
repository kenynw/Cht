package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.FleaMyViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(MyFleaPresenter.class)
public class MyFleaActivity extends BaseListActivity<MyFleaPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_flea_my);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new FleaMyViewHolder(parent, getPresenter());
    }

    @Override
    protected int getLayout() {
        return R.layout.flea_activity_my;
    }
}
