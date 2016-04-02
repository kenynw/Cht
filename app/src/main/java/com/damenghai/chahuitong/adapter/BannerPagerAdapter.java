package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.damenghai.chahuitong.model.bean.Banner;
import com.damenghai.chahuitong.module.web.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<Banner> mList;

    private List<View> mViews;

    private ImageLoader mImageLoader;

    public BannerPagerAdapter(Context context, List<Banner> list) {
        this.mContext = context;
        this.mList = list;
        mViews = new ArrayList<>();
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Banner banner = mList.get(position);

        ImageView iv = new ImageView(mContext);
        mImageLoader.displayImage(banner.getImage(), iv);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", banner.getLink());
                mContext.startActivity(intent);
            }
        });

        mViews.add(iv);
        container.addView(iv);
        return  iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
