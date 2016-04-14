package com.damenghai.chahuitong.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.facebook.common.util.StreamUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsCartViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.tv_goods_score)
    TextView mTvScore;

    @Bind(R.id.tv_goods_origin)
    TextView mTvOrigin;

    public GoodsCartViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_goods);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Goods goods) {
        super.setData(goods);
        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_price()));
        if (!TextUtils.isEmpty(goods.getOrigin())) {
            mTvOrigin.setText(goods.getOrigin());
            mTvOrigin.setVisibility(View.VISIBLE);
        } else {
            mTvOrigin.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(goods.getRecommend_score())) {
            SpannableString spanText = new SpannableString(String.format(getContext().getString(R.string.text_recommend_score), goods.getRecommend_score()));
            spanText.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorAccent)),
                    4, 4 + goods.getRecommend_score().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTvScore.setVisibility(View.VISIBLE);
            mTvScore.setText(spanText);
        } else {
            mTvScore.setVisibility(View.GONE);
        }
        itemView.setOnClickListener(v -> {
            Intent view = new Intent(getContext(), GoodsDetailActivity.class);
            view.putExtra("goods_id", goods.getGoods_id());
            getContext().startActivity(view);
        });
    }
}
