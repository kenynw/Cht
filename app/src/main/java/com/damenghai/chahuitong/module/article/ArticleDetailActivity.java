package com.damenghai.chahuitong.module.article;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.service.Services;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.mall.BuyActivity;
import com.damenghai.chahuitong.module.mall.CartListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.utils.WebViewOB;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ArticleDetailPresenter.class)
public class ArticleDetailActivity extends BaseDataActivity<ArticleDetailPresenter, Article> {

    @Bind(R.id.wv_article_detail)
    WebView mWvDetail;

    @Bind(R.id.et_add)
    EditText mEtAdd;

    @Bind(R.id.btn_add)
    Button mBtnAdd;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity_detail);
        ButterKnife.bind(this);

        mWvDetail.getSettings().setJavaScriptEnabled(true);
        mWvDetail.addJavascriptInterface(new WebViewOB(this), "CHTAndroid");
        mWvDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url) && Uri.parse(url).getScheme() != null) {
                    String scheme = Uri.parse(url).getScheme();
                    if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                        String act = Uri.parse(url).getQueryParameter("act");
                        int mid = Integer.valueOf(Uri.parse(url).getQueryParameter("mid"));
                        if (act.equalsIgnoreCase("member_sns_home")) {
                            Intent intent = new Intent(ArticleDetailActivity.this, UserInfoActivity.class);
                            intent.putExtra("user_id", mid);
                            startActivity(intent);
                            return true;
                        }
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mBtnAdd.setOnClickListener(v -> checkInput());
    }

    @Override
    public void setData(Article article) {
        setToolbarTitle(article.getArticle_title());
        String url = Services.BASE_URL + "?act=article&op=article_detail&article_id=%1$s&key=%2$s";
        mWvDetail.loadUrl(String.format(url, article.getArticle_id(), LUtils.getPreferences().getString("key", "")));

    }

    private void checkInput() {
        if (mEtAdd.getText().toString().trim().isEmpty()) {
            LUtils.toast("内容不能为空");
            return;
        }
        
        getPresenter().addComment(mEtAdd.getText().toString().trim());
    }

}
