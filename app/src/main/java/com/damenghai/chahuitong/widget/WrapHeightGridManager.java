package com.damenghai.chahuitong.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class WrapHeightGridManager extends GridLayoutManager {

    public WrapHeightGridManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapHeightGridManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapHeightGridManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int sizeHeight = MeasureSpec.getSize(heightSpec);
        int modeHeight = MeasureSpec.getMode(heightSpec);

        int rows = (state.getItemCount() + (getSpanCount() - 1)) / getSpanCount();
        int height = 0;
        for (int i=0; i < rows; i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            recycler.recycleView(child);

            height += childHeight;
        }
        height = height + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(MeasureSpec.getSize(widthSpec),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
    }
}
