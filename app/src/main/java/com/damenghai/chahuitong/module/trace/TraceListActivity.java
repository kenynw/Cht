package com.damenghai.chahuitong.module.trace;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.TraceViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(TraceListPresenter.class)
public class TraceListActivity extends BaseListActivity<TraceListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_trace_commend);
        ButterKnife.bind(this);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new TraceViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.trace_activity_list;
    }
}
