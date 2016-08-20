package com.damenghai.chahuitong.module.goods;

import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.GoodsOrderViewHolder;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Goods;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * A fragment representing a list of Items.
 */
public class SampleListFragment extends BaseListFragment<SampleListPresenter, Goods> {

    @Override
    public BaseViewHolder<Goods> createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsOrderViewHolder(parent);
    }

}
