package com.damenghai.chahuitong.module.article;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.ArticleViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;

@RequiresPresenter(ArticleListPresenter.class)
public class ArticleListActivity extends BaseListActivity<ArticleListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarTitle(R.string.title_activity_article);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.article_activity_list;
    }
}
