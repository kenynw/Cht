package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.module.flea.FleaDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaViewHolder extends BaseViewHolder<Flea> {

    @Bind(R.id.dv_flea_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.tv_flea_title)
    TextView mTvTitle;

    @Bind(R.id.tv_flea_abstract)
    TextView mTvAbstract;

    @Bind(R.id.tv_flea_price)
    TextView mTvPrice;

    @Bind(R.id.tv_flea_time)
    TextView mTvTime;

    public FleaViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_list_flea);
    }

    public FleaViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Flea data) {
        mDvImage.setImageURI(Uri.parse(data.getGoods_image_url()));
        mTvTitle.setText(data.getGoods_name());
        mTvAbstract.setText(data.getGoods_abstract());
        mTvPrice.setText(data.getGoods_store_price());
        mTvTime.setText(data.getGoods_add_time());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FleaDetailActivity.class);
            intent.putExtra("flea_id", data.getGoods_id());
            getContext().startActivity(intent);
        });
    }
}
