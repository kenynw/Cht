package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Recommend;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GalleyAdapter extends RecyclerView.Adapter<GalleyAdapter.ViewHolder> {

    private static final int VISIBLE_ITEM_COUNT = 3;
    private final int mScreenWidth;
    private Context mContext;
    private List<Recommend> mData;
    private ImageLoader mImageLoader;
    private boolean mShouldResetParent = true;

    public GalleyAdapter(Context context, List<Recommend> data) {
        mContext = context;
        mData = data;
        mImageLoader = ImageLoader.getInstance();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_galley_goods, null);
        ViewHolder holder = new ViewHolder(view);

        // 重新设置图片尺寸
        int ivWidth = (mScreenWidth - parent.getPaddingLeft() * 2 - view.getPaddingRight() * 2) / VISIBLE_ITEM_COUNT;
        holder.mIvImage.setLayoutParams(new LinearLayout.LayoutParams(ivWidth, ivWidth));
        view.setLayoutParams(new LinearLayout.LayoutParams(ivWidth + view.getPaddingRight(), ViewGroup.LayoutParams.MATCH_PARENT));

        // 设置完图片尺寸，父控件的尺寸也要相应改变
        int parentHeight = (int) (ivWidth * 1.43);
        if (mShouldResetParent) {
            parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parentHeight));
            mShouldResetParent = false;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recommend goods = mData.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                mContext.startActivity(intent);
            }
        });

        String scoreLabel = mContext.getResources().getString(R.string.label_recommend_score);
        SpannableString spanText = new SpannableString(scoreLabel + goods.getRecommend_score() + "分");
        spanText.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
                scoreLabel.length(), spanText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mImageLoader.displayImage(goods.getGoods_image_url(), holder.mIvImage);
        holder.mTvName.setText(goods.getGoods_name());
        holder.mTvscore.setText(spanText);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_galley_img)
        ImageView mIvImage;

        @Bind(R.id.item_galley_name)
        TextView mTvName;

        @Bind(R.id.item_galley_score)
        TextView mTvscore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}