package com.damenghai.chahuitong.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class WrapHeightListManager extends LinearLayoutManager {
    public WrapHeightListManager(Context context) {
        super(context);
    }

    public WrapHeightListManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapHeightListManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int sizeWidth = MeasureSpec.getSize(widthSpec);
        int modeWidth = MeasureSpec.getMode(widthSpec);
        int sizeHeight = MeasureSpec.getSize(heightSpec);
        int modeHeight = MeasureSpec.getMode(heightSpec);

        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            recycler.recycleView(child);

            if (getOrientation() == HORIZONTAL) {
                width += childWidth;
            } else {
                height += childHeight;
            }

        }

        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height
        );
    }

}
