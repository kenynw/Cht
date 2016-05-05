package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GalleyAdapter extends RecyclerView.Adapter<GalleyAdapter.ViewHolder> {

    private Context mContext;

    private List<Goods> mData;

    public GalleyAdapter(Context context, List<Goods> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_galley_goods, null);
        ViewHolder holder = new ViewHolder(view);

        // 重新设置图片尺寸
        int itemHeight = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
        int imageSize = (int) (itemHeight / 1.43);
        int itemWidth = (int) (itemHeight / 1.43 + view.getPaddingRight() + view.getPaddingLeft());
        holder.mDvImage.setLayoutParams(new LinearLayout.LayoutParams(imageSize, imageSize));
        view.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, itemHeight));

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Goods goods = mData.get(position);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            intent.putExtra("goods_id", goods.getGoods_id());
            mContext.startActivity(intent);
        });

        String scoreLabel = mContext.getResources().getString(R.string.label_recommend_score);
        SpannableString spanText = new SpannableString(scoreLabel + goods.getRecommend_score() + "分");
        spanText.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
                scoreLabel.length(), spanText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.mDvImage.setImageURI(Uri.parse(goods.getGoods_image_url()));
        holder.mTvName.setText(goods.getGoods_name());
        holder.mTvScore.setText(spanText);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_galley_img)
        ImageView mDvImage;

        @Bind(R.id.item_galley_name)
        TextView mTvName;

        @Bind(R.id.item_galley_score)
        TextView mTvScore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}