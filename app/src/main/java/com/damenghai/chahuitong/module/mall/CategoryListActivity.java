package com.damenghai.chahuitong.module.mall;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.CateViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.DividerGridItemDecoration;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

@RequiresPresenter(CategoryListPresenter.class)
public class CategoryListActivity extends BaseListActivity<CategoryListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_category);
        getListView().setLayoutManager(new GridLayoutManager(this, 3));
        getListView().addItemDecoration(new SpaceDecoration(LUtils.dp2px(8)));
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new CateViewHolder(parent, R.layout.item_list_cate);
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_category_activity_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setRefreshAble(false).setLoadMoreAble(false).setNoMoreAble(false);
    }
}
