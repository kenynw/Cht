package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.widget.ExpandTabView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsListPresenter extends BaseListActivityPresenter<GoodsListActivity, Goods> implements ExpandTabView.OnItemSelectedListener {

    private String mOp;

    private List<Category> mCategories;

    private int mGcID;

    private String mSort;

    private int mOrder = 1;

    @Override
    protected void onCreate(GoodsListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mOp = getView().getIntent().getStringExtra("op");
        if (TextUtils.isEmpty(mOp)) mOp = "goods_list";
    }

    @Override
    protected void onCreateView(GoodsListActivity view) {
        super.onCreateView(view);
        if (!TextUtils.isEmpty(getView().getIntent().getCharSequenceExtra("keyword"))) {
            getView().setKeyword(getView().getIntent().getCharSequenceExtra("keyword"));
        } else if (mOp.equals("recommend_list")) {
            getView().setToolbarTitle(R.string.title_valuator_recommend);
        }

        if (getView().getIntent().getParcelableExtra("category") != null) {
            Category category = getView().getIntent().getParcelableExtra("category");
            mGcID = category.getGc_id();
            getView().setTabText(0, category.getGc_name());
        }
        onRefresh();
        loadCategory();
    }

    @Override
    public void onRefresh() {
        GoodsModel.getInstance().getGoodsList(
                mOp,
                mSort, mOrder, 1, mGcID,
                getView().getIntent().getCharSequenceExtra("keyword"))
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        GoodsModel.getInstance().getGoodsList(
                mOp,
                mSort, mOrder, getCurPage(), mGcID,
                getView().getIntent().getCharSequenceExtra("keyword"))
                .unsafeSubscribe(getMoreSubscriber());
    }

    public void loadCategory() {
        GoodsModel.getInstance().getGoodsCategory(0).subscribe(list ->{
            mCategories = list;
            String[] result = new String[list.size()];
            for (int i=0; i<list.size(); i++) {
                result[i] = list.get(i).getGc_name();
            }
            getView().setCategory(result);
        });
    }

    @Override
    public void onItemSelected(int position, int selected) {
        if (position == 0) {
            if (mCategories != null && selected < mCategories.size()) {
                Category category = mCategories.get(selected);
                mGcID = category.getGc_id();
            }
        } else {
            mSort = position + "";
            mOrder = selected + 1;
        }
        onRefresh();
    }

    public void search(EditText editText) {
        LUtils.closeKeyboard(editText);
        getView().getIntent().putExtra("keyword", editText.getText());
        onRefresh();
    }
}
