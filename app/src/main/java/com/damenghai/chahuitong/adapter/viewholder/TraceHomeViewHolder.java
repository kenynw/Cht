package com.damenghai.chahuitong.adapter.viewholder;

import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.UserInfoPresenter;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceHomeViewHolder extends TraceCommonViewHolder{

    @Bind(R.id.tv_trace_more)
    TextView mTvMore;

    private UserInfoPresenter mPresenter;

    public TraceHomeViewHolder(ViewGroup parent, UserInfoPresenter presenter) {
        super(parent, R.layout.item_list_user_trace);
        mPresenter = presenter;
    }

    @Override
    public void setData(Trace trace) {
        super.setData(trace);
        mTvMore.setOnClickListener(v -> {
            if (trace.getRelation() == 3) {
                new AlertDialog.Builder(getContext())
                        .setItems(R.array.delete, (dialog, which) -> mPresenter.deleteTrace(trace.getTrace_id())).show();
            } else {
                new AlertDialog.Builder(getContext())
                        .setItems(R.array.inform, (dialog, which) -> {
                            new AlertDialog.Builder(getContext())
                                    .setItems(R.array.inform_content, (d, p) -> {
                                        TraceModel.getInstance().informTrace(trace.getTrace_id(), getContext().getResources().getStringArray(R.array.inform_content)[p])
                                                .subscribe(new ServiceResponse<Boolean>() {
                                                    @Override
                                                    public void onNext(Boolean o) {
                                                        LUtils.toast(R.string.toast_inform_success);
                                                    }
                                                });
                                    }).show();
                        }).show();
            }
        });
    }
}
