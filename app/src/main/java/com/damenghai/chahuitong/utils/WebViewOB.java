package com.damenghai.chahuitong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.module.mall.CartListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class WebViewOB {

    private Context mContext;

    public WebViewOB(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void showToast(String message) {
        LUtils.toast(message);
    }

    @JavascriptInterface
    public String getToken() {
        return LUtils.getPreferences().getString("key", "");
    }

    @JavascriptInterface
    public void showCartDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage(R.string.dialog_add_cart_success);
        dialog.setNegativeButton(R.string.btn_stay_shopping, null);
        dialog.setPositiveButton(R.string.btn_go_cart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, CartListActivity.class);
                mContext.startActivity(intent);
                ((Activity) mContext).finish();
            }
        });
        dialog.show();
    }

    @JavascriptInterface
    public void showDialog(String message) {
        DialogFactory.createGenericDialog(mContext, message, null).show();
    }

    @JavascriptInterface
    public void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

}
