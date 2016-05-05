package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsEditableViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.cb_favorites_edit)
    CheckBox mCbEdit;

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.goods_tv_count)
    TextView mTvCount;

    public static boolean mEditable;

    public static Map<Integer, Boolean> mStates = new HashMap<>();

    public GoodsEditableViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    public GoodsEditableViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_list_order_goods);
    }

    @Override
    public void setData(Goods goods) {
        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_price()));
        if (goods.getGoods_num() != null && Integer.parseInt(goods.getGoods_num()) > 0) {
            mTvCount.setVisibility(View.VISIBLE);
            mTvCount.setText(goods.getGoods_num());
        }
        if (mEditable) {
            mCbEdit.setVisibility(View.VISIBLE);
            mCbEdit.setChecked(mStates.containsKey(getLayoutPosition()) ? mStates.get(getLayoutPosition()) : false);
            mCbEdit.setOnCheckedChangeListener((buttonView, isChecked) -> mStates.put(getLayoutPosition(), isChecked));
            itemView.setOnClickListener(v -> mCbEdit.toggle());
        } else {
            mCbEdit.setVisibility(View.GONE);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                getContext().startActivity(intent);
            });
        }
    }
}