package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.FavoritesModel;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.damenghai.chahuitong.module.goods.GoodsFavoritesPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsFavoritesViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.iv_favorite_state)
    ImageView mIvState;

    private GoodsFavoritesPresenter mPresenter;

    public GoodsFavoritesViewHolder(ViewGroup parent, GoodsFavoritesPresenter presenter) {
        super(parent, R.layout.item_list_goods_favorites);
        mPresenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Goods goods) {
        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_price()));
        mIvState.setOnClickListener(v -> mPresenter.delFav(goods.getFav_id()));

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", goods.getGoods_id());
            getContext().startActivity(intent);
        });
    }

}
