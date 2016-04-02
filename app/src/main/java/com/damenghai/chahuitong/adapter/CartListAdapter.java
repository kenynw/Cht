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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.widget.QuantityView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder> {

    private Context mContext;

    private List<Goods> mData;

    private Map<Integer, Boolean> mStates;

    private ImageLoader mImageLoader;

    private boolean mIsEdit;

    private OnSelectedItemChangedListener mListener;

    public CartListAdapter(Context context, List<Goods> data) {
        mContext = context;
        mData = data;
        mStates = new HashMap<>();
        mImageLoader = ImageLoader.getInstance();
        initSelected();
    }

    public Map<Integer, Boolean> getStates() {
        return mStates;
    }

    public List<Goods> getCheckedItems() {
        List<Goods> items = new ArrayList<>();
        for (Integer key : mStates.keySet()) {
            boolean isChecked = mStates.get(key);
            if (isChecked) {
                items.add(mData.get(key));
            }
        }
        return items;
    }

    public void setEditable(boolean isEdit) {
        mIsEdit = isEdit;
        notifyDataSetChanged();
    }

    public List<Goods> getData() {
        return mData;
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        final Goods goods = mData.get(position);

        holder.mCb.setVisibility(View.VISIBLE);
        holder.mCb.setChecked(mStates.get(position));
        mImageLoader.displayImage(goods.getGoods_image_url(), holder.mIvThumb);
        holder.mTvName.setText(goods.getGoods_name());

        if (mIsEdit) {
            holder.mLayoutEdit.setVisibility(View.VISIBLE);
            holder.mLayoutNormal.setVisibility(View.GONE);

            holder.mQuantityView.setCount(goods.getGoods_num());
            holder.mQuantityView.setOnCountChangedListener(new QuantityView.OnCountChangedListener() {
                @Override
                public void countChanged(double count) {
                    goods.setGoods_num(count + "");
                }
            });
            holder.mTvCategory.setText(goods.getBl_id());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mCb.toggle();
                    mStates.put(position, holder.mCb.isChecked());
                }
            });
        } else {
            holder.mLayoutEdit.setVisibility(View.GONE);
            holder.mLayoutNormal.setVisibility(View.VISIBLE);
            holder.mTvPrice.setText("￥" + goods.getGoods_price());
            holder.mTvCount.setText("x" + goods.getGoods_num());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("goods_id", goods.getGoods_id());
                    mContext.startActivity(intent);
                }
            });
            holder.mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mStates.put(position, isChecked);
                    if (mListener != null) mListener.onSelectedItemChanged(getCheckedItems());
                }
            });
        }

    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_list_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnSelectedItemChangedListener(OnSelectedItemChangedListener listener) {
        mListener = listener;
    }

    private void initSelected() {
        for (int i=0; i<mData.size(); i++) {
            mStates.put(i, true);
        }
    }

    public interface OnSelectedItemChangedListener {
        void onSelectedItemChanged(List<Goods> selectedList);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cb_cart)
        CheckBox mCb;

        @Bind(R.id.iv_cart_thumb)
        ImageView mIvThumb;

        @Bind(R.id.tv_cart_name)
        TextView mTvName;

        @Bind(R.id.layout_cart_category)
        LinearLayout mLayoutEdit; // 编辑模式布局

        @Bind(R.id.count_view_cart_count)
        QuantityView mQuantityView;

        @Bind(R.id.tv_cart_category)
        TextView mTvCategory;

        @Bind(R.id.layout_cart_price)
        LinearLayout mLayoutNormal;

        @Bind(R.id.tv_cart_price)
        TextView mTvPrice;

        @Bind(R.id.tv_cart_count)
        TextView mTvCount;

        public CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
