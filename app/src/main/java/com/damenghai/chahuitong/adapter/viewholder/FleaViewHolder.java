package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.Flea;
import com.damenghai.chahuitong.module.flea.FleaDetailActivity;
import com.damenghai.chahuitong.module.flea.FleaListActivity;
import com.damenghai.chahuitong.utils.LUtils;
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

    @Bind(R.id.tv_flea_more)
    TextView mTvMore;

    public FleaViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_grid_flea_commend);
        ButterKnife.bind(this, itemView);

        int itemSize = (LUtils.getScreenWidth() - parent.getPaddingLeft() * 2) / 3;
        ViewGroup.LayoutParams lp = parent.getLayoutParams();
        lp.width = itemSize;
        lp.height = itemSize;
        itemView.setLayoutParams(lp);
    }

    public FleaViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void setData(Flea data) {
        mDvImage.setImageURI(Uri.parse(data.getGoods_image_url()));
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), FleaDetailActivity.class);
            i.putExtra("flea_id", data.getGoods_id());
            getContext().startActivity(i);
        });
    }

    public void setMore() {
        mDvImage.setVisibility(View.GONE);
        mTvMore.setVisibility(View.VISIBLE);
        itemView.setOnClickListener(v -> getContext().startActivity(new Intent(getContext(), FleaListActivity.class)));
    }

}
