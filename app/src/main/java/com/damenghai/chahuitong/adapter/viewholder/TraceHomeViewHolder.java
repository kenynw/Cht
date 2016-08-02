package com.damenghai.chahuitong.adapter.viewholder;

import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceHomeViewHolder extends TraceCommonViewHolder{

    @Bind(R.id.tv_trace_more)
    TextView mTvMore;

    public TraceHomeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_user_trace);
    }

    @Override
    public void setData(Trace trace) {
        super.setData(trace);
        mTvMore.setOnClickListener(v -> {
            if (trace.getRelation() == 1) {
                new AlertDialog.Builder(getContext())
                        .setItems(R.array.delete, (dialog, which) -> {
                            TraceModel.getInstance().delTrace(trace.getTrace_id()).subscribe(new ServiceResponse<>());
                        }).show();
            } else {
                new AlertDialog.Builder(getContext())
                        .setItems(R.array.inform, (dialog, which) -> {
                            new AlertDialog.Builder(getContext())
                                    .setItems(R.array.inform_content, (d, p) -> {
                                        TraceModel.getInstance().informTrace(trace.getTrace_id(), getContext().getResources().getStringArray(R.array.inform_content)[p])
                                                .subscribe(new ServiceResponse<>());
                                    }).show();
                        }).show();
            }
        });
    }
}
