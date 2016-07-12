package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.FleaViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(FleaListPresenter.class)
public class FleaListActivity extends BaseListActivity<FleaListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_flea);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new FleaViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.flea_activity_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_flea, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
