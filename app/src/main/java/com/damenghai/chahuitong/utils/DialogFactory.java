package com.damenghai.chahuitong.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.view.mall.CartActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class DialogFactory {

    public static Dialog createGenericDialog(Context context, @StringRes int messageRes,
                                             DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(messageRes)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, listener);
        return dialog.create();

    }

    public static Dialog createGenericDialog(Context context, String messageRes,
                                             DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(messageRes)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, listener);
        return dialog.create();

    }

    public static Dialog createCartDialog(final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(R.string.dialog_add_cart_success);
        dialog.setNegativeButton(R.string.btn_stay_shopping, null);
        dialog.setPositiveButton(R.string.btn_go_cart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, CartActivity.class));
            }
        });
        dialog.show();
        return dialog.create();
    }

}
