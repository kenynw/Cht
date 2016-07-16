package com.damenghai.chahuitong.utils;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.exgridview.PieceView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SimpleDraweePieceView extends PieceView {
    private SimpleDraweeView mSimpleDraweeView;

    public SimpleDraweePieceView(Context context) {
        super(context);
        init();
    }

    public SimpleDraweePieceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleDraweePieceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        FrameLayout layout = new FrameLayout(getContext());
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mSimpleDraweeView= new SimpleDraweeView(getContext());
        mSimpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mSimpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView tv = new TextView(getContext());
        tv.setText("长按编辑");
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(getContext().getResources().getColor(R.color.text_black_secondary));
        tv.setTextColor(getContext().getResources().getColor(R.color.white));
        tv.setPadding(0, 4, 0, 4);
        LayoutParams tvLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLayout.gravity = Gravity.BOTTOM;
        tv.setLayoutParams(tvLayout);

        layout.addView(mSimpleDraweeView);
        layout.addView(tv);
        addView(layout);
    }

    public void setImageUri(Uri uri){
        mSimpleDraweeView.setImageURI(uri);
    }

}
