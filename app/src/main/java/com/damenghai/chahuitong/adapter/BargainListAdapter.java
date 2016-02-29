package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.view.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.view.web.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BargainListAdapter extends RecyclerView.Adapter<BargainListAdapter.BargainViewHolder> {

    private Context mContext;

    private List<Bargain> mData;

    private ImageLoader mImageLoader;

    public BargainListAdapter(Context context, List<Bargain> data) {
        mContext = context;
        mData = data;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public BargainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_bargain, parent, false);
        return new BargainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BargainViewHolder holder, int position) {
        final Bargain bargain = mData.get(position);
        if (position == 0) {
            holder.mLayout.setVisibility(View.VISIBLE);
            long endTime = (bargain.getEnd_time() + 3600) * 1000L;
            new CountDownTimer(endTime - System.currentTimeMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String days = (millisUntilFinished / (1000 * 3600 * 24)) + "";
                    String hours = ((millisUntilFinished % (1000 * 3600 * 24)) / (1000 * 3600)) + "";
                    String minutes = ((millisUntilFinished % (1000 * 3600)) / (1000 * 60)) + "";
                    String seconds = (millisUntilFinished % (1000 * 60) / 1000) + "";

                    holder.mTvDays.setText(days);
                    holder.mTvHours.setText(hours);
                    holder.mTvMinutes.setText(minutes);
                    holder.mTvSecond.setText(seconds);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            holder.mLayout.setVisibility(View.GONE);
        }

        mImageLoader.displayImage(bargain.getGoods_image().getBmiddle_pic(), holder.mIvImage);
        holder.mTvName.setText(bargain.getGoods_name());
        holder.mTvPrice.setText("￥" + bargain.getGoods_price());
        holder.mTvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 加删除线
        holder.mTvDiscount.setText("￥" + bargain.getXianshi_price());

        SpannableString stockSpn = new SpannableString("已抢购" + bargain.getGoods_salenum()
                +  "份/共" + bargain.getGoods_storage() + "份");
        stockSpn.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
                3, 3 + bargain.getGoods_salenum().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.mTvStock.setText(stockSpn);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", bargain.getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addList(List<Bargain> list) {
        if (list != null) {
            for (Bargain bargain : list) {
                if (!mData.contains(bargain)) mData.add(bargain);
            }
            notifyDataSetChanged();
        }
    }

    public class BargainViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.layout_bargain_slogan)
        LinearLayout mLayout;

        @Bind(R.id.tv_bargain_day)
        TextView mTvDays;

        @Bind(R.id.tv_bargain_hour)
        TextView mTvHours;

        @Bind(R.id.tv_bargain_minute)
        TextView mTvMinutes;

        @Bind(R.id.tv_bargain_second)
        TextView mTvSecond;

        @Bind(R.id.iv_bargain_image)
        ImageView mIvImage;

        @Bind(R.id.tv_bargain_name)
        TextView mTvName;

        @Bind(R.id.tv_bargain_discount)
        TextView mTvDiscount;

        @Bind(R.id.tv_bargain_price)
        TextView mTvPrice;

        @Bind(R.id.tv_bargain_stock)
        TextView mTvStock;

        public BargainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
