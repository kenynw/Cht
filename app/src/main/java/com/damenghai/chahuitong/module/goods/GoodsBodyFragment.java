package com.damenghai.chahuitong.module.goods;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.config.Config;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsBodyFragment extends BaseFragment {

    public static final String KEY_GOODS_ID = "goods_id";

    @Bind(R.id.wv_goods_body)
    WebView mWvBody;

    public GoodsBodyFragment() {}

    public static GoodsBodyFragment newInstance(String goodsId) {
        GoodsBodyFragment fragment = new GoodsBodyFragment();
        Bundle args = new Bundle();
        args.putString(KEY_GOODS_ID, goodsId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment_body, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mWvBody.loadUrl(Config.BASE_GOODS_BODY_URL + getArguments().getString(KEY_GOODS_ID));

            mWvBody.getSettings().setJavaScriptEnabled(true);

            //自适应屏幕
            mWvBody.getSettings().setUseWideViewPort(true);
            mWvBody.getSettings().setLoadWithOverviewMode(true);

            mWvBody.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });
        }
    }
}
