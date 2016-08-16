package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.model.bean.Banner;
import com.damenghai.chahuitong.model.bean.SpecialItem;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<SpecialItem> mList;

    private List<View> mViews;

    public BannerPagerAdapter(Context context, List<SpecialItem> list) {
        this.mContext = context;
        this.mList = list;
        mViews = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final SpecialItem item = mList.get(position);

        SimpleDraweeView dv = new SimpleDraweeView(mContext);
        dv.setImageURI(Uri.parse(item.getImage()));
        dv.setOnClickListener(v -> {
            Intent intent = new Intent();
            switch (item.getType()) {
                case "url" :
                    if (item.getData().startsWith("http://") || item.getData().startsWith("https://")) {
                        intent.setClass(mContext, WebViewActivity.class);
                        intent.putExtra("url", item.getData());
                    } else {
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(item.getData()));
                    }
                    break;
                case "keyword" :
                    intent.setClass(mContext, GoodsListActivity.class);
                    intent.putExtra("keyword", item.getData());
                    break;
                case "special" :

                    break;
                default :

                    break;
            }
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
