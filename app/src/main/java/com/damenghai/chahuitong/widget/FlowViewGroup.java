package com.damenghai.chahuitong.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FlowViewGroup extends ViewGroup {

    private int mColumns;

    private float mHorizontalSpace;

    private float mVerticalSpace;

    private int mTextColor;

    private float mTextSize;

    private int mTextBackground;

    private OnTextClickListener mListener;

    public FlowViewGroup(Context context) {
        this(context, null);
    }

    public FlowViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowViewGroup);
        mHorizontalSpace = ta.getDimension(R.styleable.FlowViewGroup_horizontalSpace, 0);
        mVerticalSpace = ta.getDimension(R.styleable.FlowViewGroup_verticalSpace, 0);
        mColumns = ta.getInt(R.styleable.FlowViewGroup_columns, 3);
        mTextColor = ta.getColor(R.styleable.FlowViewGroup_textColor,
                getResources().getColor(R.color.text_black_primary));
        mTextSize = ta.getDimension(R.styleable.FlowViewGroup_textSize,
                getResources().getDimension(R.dimen.text_size_body_material));
        mTextBackground = ta.getResourceId(R.styleable.FlowViewGroup_textBackground, android.R.color.white);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lineHeight = 0;

        int count = getChildCount();

        int childWidth = (int) ((sizeWidth - getPaddingLeft() - getPaddingRight() - (mColumns - 1) * mHorizontalSpace) / mColumns);

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            LayoutParams lp = child.getLayoutParams();

            int childMeasureHeightSpec = getChildMeasureSpec(heightMeasureSpec,
                    getPaddingLeft() + getPaddingRight(), lp.height);
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), childMeasureHeightSpec);

            int childHeight = child.getMeasuredHeight();

            // 换行
            if (i > 0 && i % mColumns == 0) {
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == count - 1) {
                height += lineHeight;
            }

        }

        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : (int) (height + getPaddingTop() + getPaddingBottom() + mVerticalSpace * (mColumns - 1))
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left;
        int top;
        int right;
        int bottom;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int column = i % mColumns;
            int rows = i / mColumns;
            left = (int) (column * child.getMeasuredWidth() + getPaddingLeft() + mHorizontalSpace * column);
            top = (int) (rows * child.getMeasuredHeight() + getPaddingTop() + mVerticalSpace * rows);
            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setText(String[] texts) {
        for (final String text : texts) {
            TextView tv = new TextView(getContext());
            tv.setText(text);
            tv.setTextColor(mTextColor);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            tv.setBackgroundResource(mTextBackground);
            tv.setGravity(Gravity.CENTER);
            tv.setHeight((int) getResources().getDimension(R.dimen.widget_small_height));
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) mListener.onTextClickListener(text);
                }
            });
            addView(tv);
        }
        requestLayout();
    }

    public void setOnTextClickListener(OnTextClickListener listener) {
        mListener = listener;
    }

    public interface OnTextClickListener {
        void onTextClickListener(String text);
    }

}
