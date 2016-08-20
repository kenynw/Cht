package com.damenghai.chahuitong.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsOrderViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.dv_goods_thumb)
    ImageView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.tv_goods_num)
    TextView mTvNum;

    public GoodsOrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_order_goods);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Goods data) {
        mDvThumb.setImageURI(Uri.parse(data.getGoods_image_url()));
        mTvName.setText(data.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), data.getGoods_price()));
        mTvNum.setText(String.format(getContext().getString(R.string.text_order_goods_count), data.getGoods_num()));
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", data.getGoods_id());
            getContext().startActivity(intent);
        });
    }
}
