package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.Trace;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceViewHolder extends BaseViewHolder<Trace> {

    @Bind(R.id.dv_item_image)
    SimpleDraweeView mDvImage;

    public TraceViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_grid_image);
    }

    public TraceViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);

        // 重新设置图片尺寸
        int itemSize = (LUtils.getScreenWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / 3;
        mDvImage.setLayoutParams(new LinearLayout.LayoutParams(itemSize, itemSize));
    }

    @Override
    public void setData(Trace data) {
        mDvImage.setImageURI(Uri.parse(data.getTrace_image()));

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TraceDetailActivity.class);
            intent.putExtra("trace_id", data.getTrace_id());
            getContext().startActivity(intent);
        });
    }
}
