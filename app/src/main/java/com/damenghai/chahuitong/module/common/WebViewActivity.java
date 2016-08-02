package com.damenghai.chahuitong.module.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.utils.WebViewOB;
import com.damenghai.chahuitong.module.mall.BuyActivity;
import com.damenghai.chahuitong.module.mall.CartActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class WebViewActivity extends BaseActivity {
	private String mUrl;

    private WebSettings mSettings;

    @Bind(R.id.web_view)
    WebView mWebView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        mUrl = getIntent().getStringExtra("url");

        handleIntent();

		initWebView();
	}

    private void handleIntent() {
        if (getIntent().getData() != null) {
            Uri uri = getIntent().getData();
            mUrl = uri.toString().replace("cht", "http");
        }
    }

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    protected void initWebView() {
        showProgressBar();

        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setBlockNetworkImage(true);
        mSettings.setDomStorageEnabled(true);
        mSettings.setDefaultTextEncodingName("utf-8");
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //往浏览器标识字符串里添加自定义的字符串，用于服务器判断是否为客户端
        if (!mSettings.getUserAgentString().endsWith("/android")) {
            mSettings.setUserAgentString(mSettings.getUserAgentString() + "/android");
        }

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        mWebView.addJavascriptInterface(new WebViewOB(this), "CHTAndroid");
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

    }

    /**
     * 通过key查找value值
     *
     * @param key
     * @return
     */
	private String getCookieValue(String key) {
		if(!TextUtils.isEmpty(key)) {
            CookieManager cookieManager = CookieManager.getInstance();
			String cookie = cookieManager.getCookie(mUrl);
			if(!TextUtils.isEmpty(cookie)) {
				String[] cookies = cookie.split(";");
				for(String set : cookies) {
					String[] values = set.split("=");
					if(key.equals(values[0]) && values.length > 1) return set.split("=")[1];
				}
			}
		}
		return "";
	}

    private void hideLoadding() {
        hideProgressBar();
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> map = new HashMap<>();
        map.put("url", mUrl);
        MobclickAgent.onEvent(this, "WebView", map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class MyWebViewClient extends WebViewClient {

        private Map<String, String> getParam(String url) {
            Map<String, String> map = new HashMap<>();
            String paramStr = url.substring(url.indexOf("?") + 1);
            String[] params = paramStr.split("&");
            for (String str : params) {
                String[] values = str.split("=");
                if (values.length >= 2) map.put(values[0], values[1]);
            }
            return map;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.endsWith("login")) {
                openActivity(LoginActivity.class);
                return true;
            }
            else if (url.contains("buy_step1")) {
                Map<String, String> params = getParam(url);
                Bundle bundle = new Bundle();
                for (String key : params.keySet()) {
                    bundle.putString(key, params.get(key));
                }
                openActivity(BuyActivity.class, bundle);
                return true;
            } else if (url.endsWith("cart")) {
                openActivity(CartActivity.class);
                return true;
            }
            else if(url.contains("tel:0592-5990900")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:05925990900"));
                startActivity(intent);
                return true;
            } else if(url.contains("114.215.108.10:30001") || url.contains("95516")) {
                view.loadUrl(url);
                mUrl = url;
                return super.shouldOverrideUrlLoading(view, url);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                openActivity(WebViewActivity.class, bundle);
                return true;
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mSettings.setBlockNetworkImage(false);
            hideLoadding();

            CookieManager cm = CookieManager.getInstance();
            cm.setCookie(url, "key=" + LUtils.getPreferences().getString("key", ""));
        }

    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            showShort(message);
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setToolbarTitle(title);
        }
    }

}
