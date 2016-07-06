package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.PopularViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(PopularListPresenter.class)
public class PopularListActivity extends BaseListActivity<PopularListPresenter> {

    @Bind(R.id.btn_toolbar_search)
    Button mBtnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mBtnSearch.setOnClickListener(v -> startActivity(new Intent(this, FindUserListActivity.class)));
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.pupular_activity_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setHasItemDecoration(false);
    }

}
