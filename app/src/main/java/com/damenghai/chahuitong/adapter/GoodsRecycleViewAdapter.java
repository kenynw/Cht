package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.view.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.view.web.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsRecycleViewAdapter extends BaseRecycleListAdapter<Goods, GoodsRecycleViewAdapter.GoodsViewHolder> {

    private boolean mEditable;

    private Map<Integer, Boolean> mStateList;

    public GoodsRecycleViewAdapter(Context context, List<Goods> data) {
        super(context, data);
        initState();
    }

    private void initState() {
        mStateList = new HashMap<>();
        for (int i=0; i<mData.size(); i++) {
            mStateList.put(i, false);
        }
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, final int position) {
        final Goods goods = mData.get(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(goods.getGoods_image_url(), holder.mIvThumb);
        holder.mTvName.setText(goods.getName());
        holder.mTvPrice.setText("￥" + goods.getGoods_price());
        holder.mTvCount.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                mContext.startActivity(intent);
            }
        });

        final CheckBox cb = holder.mCbEdit;
        if (mEditable) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.toggle();
                }
            });
            cb.setVisibility(View.VISIBLE);
            cb.setChecked(mStateList.get(position));
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mStateList.put(position, isChecked);
                }
            });
        } else {
            cb.setVisibility(View.INVISIBLE);
        }
    }

    public void setEditable(boolean editable) {
        this.mEditable = editable;
        notifyDataSetChanged();
    }

    public Map<Integer, Boolean> getStateList() {
        return mStateList;
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_list_order_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cb_favorites_edit)
        CheckBox mCbEdit;

        @Bind(R.id.goods_iv_thumb)
        ImageView mIvThumb;

        @Bind(R.id.goods_tv_title)
        TextView mTvName;

        @Bind(R.id.goods_tv_price)
        TextView mTvPrice;

        @Bind(R.id.goods_tv_count)
        TextView mTvCount;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
