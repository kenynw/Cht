package com.damenghai.chahuitong.model.bean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Sgun on 15/8/18.
 */
public class Personal implements Serializable {

    private int mTextRes;

    private int mIconRes;

    public Personal(int textRes, int icon) {
        this.mTextRes = textRes;
        this.mIconRes = icon;
    }

    public int getTextRes() {
        return mTextRes;
    }

    public void setTextRes(int textRes) {
        mTextRes = textRes;
    }

    public int getIcon() {
        return mIconRes;
    }

    public void setIcon(int iconRes) {
        mIconRes = iconRes;
    }

}
