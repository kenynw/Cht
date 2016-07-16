package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.module.common.ImageBrowseActivity;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;

    private ArrayList<Image> mList;

    private List<View> mViews;

    public ImagePagerAdapter(Context context, ArrayList<Image> list) {
        mContext = context;
        mList = list;
        mViews = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(mContext.getResources().getDrawable(R.mipmap.def_image_loading), ScalingUtils.ScaleType.CENTER_CROP)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        SimpleDraweeView dv = new SimpleDraweeView(mContext);
        dv.setHierarchy(hierarchy);
        dv.setImageURI(Uri.parse(mList.get(position).getThumb_mid()));
        dv.setOnClickListener(v -> {
            Intent imageIntent = new Intent(mContext, ImageBrowseActivity.class);
            imageIntent.putParcelableArrayListExtra("images", mList);
            imageIntent.putExtra("position", position);
            mContext.startActivity(imageIntent);
        });

        mViews.add(dv);
        container.addView(dv);
        return  dv;
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
