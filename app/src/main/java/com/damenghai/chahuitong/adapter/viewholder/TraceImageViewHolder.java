package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceImageViewHolder extends BaseViewHolder<Trace> {

    @Bind(R.id.dv_item_image)
    SimpleDraweeView mDvImage;

    public TraceImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_trace_image);
        int itemSize = (LUtils.getScreenWidth() - (parent.getPaddingLeft() + parent.getPaddingRight()) * 2) / 3;
        itemView.setLayoutParams(new ViewGroup.LayoutParams(itemSize, itemSize));
        ButterKnife.bind(this, itemView);
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
