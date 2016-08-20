package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsBargainViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;

@RequiresPresenter(GoodsBargainPresenter.class)
public class GoodsBargainActivity extends BaseListActivity<GoodsBargainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_bargain);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.special_activity_bargain;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsBargainViewHolder(parent);
    }

}
