package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GalleyAdapter extends RecyclerArrayAdapter<Goods> {

    public GalleyAdapter(Context context, List<Goods> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    static class ViewHolder extends BaseViewHolder<Goods> {
        @Bind(R.id.item_galley_img)
        ImageView mDvImage;

        @Bind(R.id.item_galley_name)
        TextView mTvName;

        @Bind(R.id.item_galley_price)
        TextView mTvPrice;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_galley_goods);
            ButterKnife.bind(this, itemView);

            // 重新设置图片尺寸
            int itemHeight = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
            int itemWidth = (int) (itemHeight / 1.43 + itemView.getPaddingRight() + itemView.getPaddingLeft());
            mDvImage.setLayoutParams(new LinearLayout.LayoutParams((int) (itemHeight / 1.43), (int) (itemHeight / 1.43)));
            itemView.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, itemHeight));
        }

        @Override
        public void setData(Goods goods) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                getContext().startActivity(intent);
            });

            mDvImage.setImageURI(Uri.parse(goods.getGoods_image()));
            mTvName.setText(goods.getGoods_name());
            mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_promotion_price()));
        }
    }

}