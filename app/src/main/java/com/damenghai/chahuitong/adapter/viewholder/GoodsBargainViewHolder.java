package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsBargainViewHolder extends BaseViewHolder<Bargain> {

    @Bind(R.id.tv_bargain_day)
    TextView mTvDays;

    @Bind(R.id.tv_bargain_hour)
    TextView mTvHours;

    @Bind(R.id.tv_bargain_minute)
    TextView mTvMinutes;

    @Bind(R.id.tv_bargain_second)
    TextView mTvSecond;

    @Bind(R.id.layout_bargain_timer)
    LinearLayout mLayoutTimer;

    @Bind(R.id.dv_bargain_image)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_bargain_name)
    TextView mTvName;

    @Bind(R.id.tv_bargain_discount)
    TextView mTvPrice;

    @Bind(R.id.tv_bargain_price)
    TextView mTvOldPrice;

    @Bind(R.id.tv_bargain_stock)
    TextView mTvStock;

    public GoodsBargainViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_bargain);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Bargain goods) {
        if (getLayoutPosition() == 0) {
            mLayoutTimer.setVisibility(View.VISIBLE);
            long endTime = (goods.getEnd_time() + 3600) * 1000L;
            new CountDownTimer(endTime - System.currentTimeMillis(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String days = (millisUntilFinished / (1000 * 3600 * 24)) + "";
                    String hours = ((millisUntilFinished % (1000 * 3600 * 24)) / (1000 * 3600)) + "";
                    String minutes = ((millisUntilFinished % (1000 * 3600)) / (1000 * 60)) + "";
                    String seconds = (millisUntilFinished % (1000 * 60) / 1000) + "";

                    mTvDays.setText(days);
                    mTvHours.setText(hours);
                    mTvMinutes.setText(minutes);
                    mTvSecond.setText(seconds);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            mLayoutTimer.setVisibility(View.GONE);
        }

        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getXianshi_price()));
        mTvOldPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_price()));
        mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 加删除线

        SpannableString stockSpn = new SpannableString(String.format(getContext().getString(R.string.text_xianshi_storage), goods.getGoods_salenum(), goods.getGoods_storage()));
        stockSpn.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorAccent)),
                3, 3 + goods.getGoods_salenum().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvStock.setText(stockSpn);

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", goods.getGoods_id());
            getContext().startActivity(intent);
        });
    }
}
