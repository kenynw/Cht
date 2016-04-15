package com.damenghai.chahuitong.module.mall;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsCartViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.widget.ExpandTabView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GoodsListPresenter.class)
public class GoodsListActivity extends BaseListActivity<GoodsListPresenter> {

    @Bind(R.id.expand_goods_list_order)
    ExpandTabView mTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_goods_list);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.goods_activity_list;
    }

    @Override
    protected int getEmptyRes() {
        return R.layout.fragment_empty;
    }

    @Override
    protected int getLoadMoreRes() {
        return R.layout.footer_load_more;
    }

    @Override
    protected int getNoMoreRes() {
        return R.layout.footer_no_more;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsCartViewHolder(parent);
    }

    public void setCategory(String[] list) {
        mTabView.setData(0, list);
        mTabView.setOnItemSelectedListener(getPresenter());
    }

}
