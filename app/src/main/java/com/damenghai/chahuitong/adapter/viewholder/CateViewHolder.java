package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CateViewHolder extends BaseViewHolder<Category> {

    @Bind(R.id.dv_cate_image)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_cate_name)
    TextView mTvName;

    public CateViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_list_category_image);
    }

    public CateViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        if (res == R.layout.item_list_cate) {
            int size = (LUtils.getScreenWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / 3;
            itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size));
        }
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Category data) {
        mDvThumb.setImageURI(Uri.parse(data.getGc_thumb()));
        mTvName.setText(data.getGc_name());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), GoodsListActivity.class);
            i.putExtra("op", "goods_list");
            i.putExtra("category", data);
            getContext().startActivity(i);
        });
    }
}
