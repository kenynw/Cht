package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.module.order.OrderListPresenter;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.module.mall.PayActivity;
import com.damenghai.chahuitong.module.order.OrderDetailActivity;
import com.damenghai.chahuitong.module.order.OrderListActivity;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.widget.WrapHeightListView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListAdapter extends BaseListAdapter<Order> {

    private OrderListPresenter mPresenter;

    public OrderListAdapter(Context context, List<Order> data, OrderListPresenter presenter, int resId) {
        super(context, data, resId);
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getOrder_state();
    }

    @Override
    public void convert(ViewHolder holder, final Order order) {
        holder.setText(R.id.order_tv_store, order.getStore_name())
                .setText(R.id.order_tv_state, order.getState_desc())
                .setText(R.id.order_tv_count, "共" + order.getExtend_order_goods().size() + "件商品")
                .setText(R.id.order_tv_total, "￥" + order.getOrder_amount());

        WrapHeightListView goodsListView = holder.getView(R.id.order_lv_goods);
        if(order.getExtend_order_goods() != null) {
            GoodsListAdapter goodsListAdapter = new GoodsListAdapter(mContext,
                    order.getExtend_order_goods(), R.layout.item_list_order_goods);
            goodsListView.setAdapter(goodsListAdapter);
            goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("order", order);
                    mContext.startActivity(intent);
                }
            });
        }

        switch (getItemViewType(holder.getPosition())) {
            case OrderListActivity.STATE_UNPAID :
                // 待付款
                holder.setVisibility(R.id.order_btn_left, View.VISIBLE)
                        .setVisibility(R.id.order_btn_right, View.VISIBLE)
                        .setOnClickListener(R.id.order_btn_left, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DialogFactory.createGenericDialog(mContext, R.string.dialog_cancel_order,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                mPresenter.cancel(order.getOrder_id());
                                            }
                                        }).show();
                            }
                        })
                        .setOnClickListener(R.id.order_btn_right, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, PayActivity.class);
                        intent.putExtra("order", order);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case OrderListActivity.STATE_PAID :
                // 待发货
                holder.setVisibility(R.id.order_btn_left, View.GONE)
                        .setVisibility(R.id.order_btn_right, View.GONE);
                break;
            case OrderListActivity.STATE_RECEIVE :
                // 待收货 查看物流：http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=267
                holder.setVisibility(R.id.order_btn_left, View.GONE)
                        .setText(R.id.order_btn_left, R.string.btn_view_delivery)
                        .setOnClickListener(R.id.order_btn_left, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, WebViewActivity.class);
                                intent.putExtra("url", "http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=" + order.getOrder_id());
                                mContext.startActivity(intent);
                            }
                        })
                        .setVisibility(R.id.order_btn_right, View.VISIBLE)
                        .setText(R.id.order_btn_right, R.string.btn_sure_order)
                        .setOnClickListener(R.id.order_btn_right, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DialogFactory.createGenericDialog(mContext, R.string.dialog_sure_order,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                mPresenter.sure(order.getOrder_id());
                                            }
                                        }).show();
                            }
                        });
                break;
            case OrderListActivity.STATE_UNCOMMENT :
                holder.setVisibility(R.id.order_btn_left, View.GONE)
                        .setText(R.id.order_btn_left, R.string.btn_view_delivery)
                        .setOnClickListener(R.id.order_btn_left, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, WebViewActivity.class);
                                intent.putExtra("url", "http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=" + order.getOrder_id());
                                mContext.startActivity(intent);
                            }
                        })
                        .setVisibility(R.id.order_btn_right, View.VISIBLE)
                        .setText(R.id.order_btn_right, R.string.btn_comment)
                        .setOnClickListener(R.id.order_btn_right, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, WebViewActivity.class);
                                intent.putExtra("url", "http://www.chahuitong.com/wap/index.php/Home/Index/pingjiaorder/oid/" + order.getOrder_id());
                                mContext.startActivity(intent);
                            }
                        });
                break;
            default :
                // 其他
                holder.setVisibility(R.id.order_btn_left, View.GONE)
                        .setVisibility(R.id.order_btn_right, View.GONE);
                break;
        }

    }

}

