package com.damenghai.chahuitong.module.order;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.FavoritesGoodsViewHolder;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.mall.PayActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderViewHolder extends BaseViewHolder<Order> {

    @Bind(R.id.order_tv_store)
    TextView mTvStore;

    @Bind(R.id.order_tv_state)
    TextView mTvState;

    @Bind(R.id.lv_order_goods)
    RecyclerView mLvGoods;

    @Bind(R.id.order_tv_count)
    TextView mTvCount;

    @Bind(R.id.order_tv_total)
    TextView mTvTotal;

    @Bind(R.id.order_btn_left)
    Button mBtnLeft;

    @Bind(R.id.order_btn_right)
    Button mBtnRight;

    private SwipeRefreshLayout.OnRefreshListener mListener;

    public OrderViewHolder(ViewGroup parent, SwipeRefreshLayout.OnRefreshListener refreshListener) {
        super(parent, R.layout.item_list_order);
        mListener = refreshListener;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Order order) {
        mTvStore.setText(order.getStore_name());
        mTvState.setText(order.getState_desc());
        mTvCount.setText(String.format(getContext().getString(R.string.text_order_count), order.getExtend_order_goods().size()));
        mTvTotal.setText(String.format(getContext().getString(R.string.text_rmb), order.getOrder_amount()));

        OrderGoodsListAdapter adapter = new OrderGoodsListAdapter(getContext(), order.getExtend_order_goods());
        mLvGoods.setLayoutManager(new LinearLayoutManager(getContext()));
        mLvGoods.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> startOrderInfo(order));
        itemView.setOnClickListener(v -> startOrderInfo(order));

        if (order.getPay_amount() > 0) {
            mBtnRight.setVisibility(View.VISIBLE);
            mBtnRight.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), PayActivity.class);
                intent.putExtra("order", order);
                getContext().startActivity(intent);
            });
        }

        if (order.isIf_cancel()) {
            mBtnLeft.setVisibility(View.VISIBLE);
            mBtnLeft.setOnClickListener(v ->
                    DialogFactory.createGenericDialog(getContext(), R.string.dialog_cancel_order, ((dialog, which) -> {
                        OrderModel.getInstance().cancelOrder(order.getOrder_id())
                                .unsafeSubscribe(new ServiceResponse<String>() {
                                    @Override
                                    public void onNext(String result) {
                                        mListener.onRefresh();
                                    }
                                });
                    })).show()
            );
        }

        if (order.isIf_receive()) {
            mBtnRight.setVisibility(View.VISIBLE);
            mBtnRight.setText(R.string.btn_order_sure);
            mBtnRight.setOnClickListener(v ->
                    DialogFactory.createGenericDialog(getContext(), R.string.dialog_sure_order, (dialog, which) -> {
                        OrderModel.getInstance().sureOrder(order.getOrder_id())
                                .unsafeSubscribe(new ServiceResponse<String>() {
                                    @Override
                                    public void onNext(String result) {
                                        mListener.onRefresh();
                                    }
                                });
                    }).show()
            );
        }

        if (order.isIf_deliver()) {
            mBtnLeft.setVisibility(View.VISIBLE);
            mBtnLeft.setText(R.string.btn_order_delivery);
            mBtnLeft.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", "http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=" + order.getOrder_id());
                getContext().startActivity(intent);
            });
        }

        if (order.isIf_evaluation()) {
            mBtnRight.setVisibility(View.VISIBLE);
            mBtnRight.setText(R.string.btn_order_comment);
            mBtnRight.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), WriteCommentActivity.class);
                intent.putExtra("order", order);
                getContext().startActivity(intent);
            });
        }

    }

    private void startOrderInfo(Order order) {
        Intent intent = new Intent(getContext(), OrderDetailActivity.class);
        intent.putExtra("order", order);
        getContext().startActivity(intent);
    }

    public static class OrderGoodsListAdapter extends RecyclerArrayAdapter<Goods> {

        private OnItemClickListener mListener;

        public OrderGoodsListAdapter(Context context, List<Goods> objects) {
            super(context, objects);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            FavoritesGoodsViewHolder viewHolder = new FavoritesGoodsViewHolder(parent, R.layout.item_list_order_goods);
            if (mListener != null) {
                viewHolder.setOnItemClickListener(mListener);
            }
            return viewHolder;
        }

        @Override
        public void setOnItemClickListener(OnItemClickListener listener) {
            super.setOnItemClickListener(listener);
            mListener = listener;
        }

    }

}
