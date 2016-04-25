package com.damenghai.chahuitong.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GalleyLinearLayout extends LinearLayoutManager {

    private static final int VISIBLE_ITEM_COUNT = 3;

    public GalleyLinearLayout(Context context) {
        super(context);
    }

    public GalleyLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public GalleyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int modeHeight = MeasureSpec.getMode(heightSpec);
        int sizeWidth = MeasureSpec.getSize(widthSpec);
        int sizeHeight = MeasureSpec.getSize(heightSpec);

        int childWidth = (LUtils.getScreenWidth() - getPaddingLeft() * 2 - LUtils.dp2px(8) * (VISIBLE_ITEM_COUNT - 1)) / VISIBLE_ITEM_COUNT;
        int height = (int) (childWidth * 1.43 + getPaddingTop() + getPaddingBottom());

        setMeasuredDimension(sizeWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
    }
}
