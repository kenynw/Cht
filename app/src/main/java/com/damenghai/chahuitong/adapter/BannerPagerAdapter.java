package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.model.bean.Banner;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<Banner> mList;

    private List<View> mViews;

    public BannerPagerAdapter(Context context, List<Banner> list) {
        this.mContext = context;
        this.mList = list;
        mViews = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Banner banner = mList.get(position);

        SimpleDraweeView dv = new SimpleDraweeView(mContext);
        dv.setImageURI(Uri.parse(banner.getImage()));
        dv.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("url", banner.getLink());
            mContext.startActivity(intent);
        });

        mViews.add(dv);
        container.addView(dv);
        return dv;
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
