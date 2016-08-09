package com.damenghai.chahuitong.module.article;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Article;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ArticleDetailPresenter.class)
public class ArticleDetailActivity extends BaseDataActivity<ArticleDetailPresenter, Article> {

    @Bind(R.id.tv_article_detail)
    WebView mTvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Article article) {
//        mTvDetail.setText(Html.fromHtml(article.get));
        int id = getIntent().getIntExtra("article_id", 0);
        mTvDetail.loadUrl("http://api.chahuitong.com/?act=article&op=article_detail&article_id" + id);
    }
}
