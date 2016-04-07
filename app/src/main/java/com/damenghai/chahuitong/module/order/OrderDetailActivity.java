package com.damenghai.chahuitong.module.order;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.GoodsListAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.widget.WrapHeightListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, Order> {

    @Bind(R.id.order_detail_tv_state)
    TextView mTvState;

    @Bind(R.id.order_detail_tv_store)
    TextView mTvStore;

    @Bind(R.id.order_detail_goods_list)
    WrapHeightListView mLvGoods;

    @Bind(R.id.order_detail_tv_freight)
    TextView mTvFreight;

    @Bind(R.id.order_detail_tv_total)
    TextView mTvTotal;

    @Bind(R.id.order_detail_tv_order_num)
    TextView mTvOrderNo;

    @Bind(R.id.order_detail_tv_pay_num)
    TextView mTvPayNo;

    @Bind(R.id.order_detail_tv_time)
    TextView mTvCreateTime;

    @Bind(R.id.order_detail_btn_layout)
    LinearLayout mLayoutBtn;

    @Bind(R.id.order_detail_btn_left)
    Button mBtnLeft;

    @Bind(R.id.order_detail_btn_right)
    Button mBtnRight;

    @Bind(R.id.order_detail_ll_phone)
    LinearLayout mLlPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setToolbarTitle(R.string.title_activity_order_detail);
        ButterKnife.bind(this);
        mLlPhone.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + getString(R.string.text_phone_number)))));
    }

    @Override
    public void setData(Order order) {
        super.setData(order);
        if (order != null) {
            mTvState.setText(order.getState_desc());
            mTvStore.setText(order.getStore_name());
            mTvFreight.setText(String.format(getString(R.string.label_rmb), order.getShipping_fee()));
            mTvTotal.setText(String.format(getString(R.string.label_rmb), order.getOrder_amount()));
            mTvOrderNo.setText(String.format(getString(R.string.label_order_num), order.getOrder_sn()));
            mTvPayNo.setText(String.format(getString(R.string.label_pay_num), order.getPay_sn()));
            mTvCreateTime.setText(String.format(getString(R.string.label_time_create), DateUtils.getDateTime(order.getAdd_time())));

            ArrayList<Goods> data = order.getExtend_order_goods();
            if (data != null) {
                GoodsListAdapter adapter = new GoodsListAdapter(this, data, R.layout.item_list_order_goods);
                mLvGoods.setAdapter(adapter);
                mLvGoods.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(OrderDetailActivity.this, GoodsDetailActivity.class);
                    intent.putExtra("goods_id", order.getExtend_order_goods().get(position).getGoods_id());
                    startActivity(intent);
                });
            }

            switch (order.getOrder_state()) {
                case OrderListActivity.STATE_UNPAID:
                    mBtnLeft.setOnClickListener(view -> cancelOrder());
                    mBtnRight.setOnClickListener(view -> getPresenter().payOrder());
                    break;
                case OrderListActivity.STATE_RECEIVE:
                    mBtnLeft.setText(R.string.btn_view_delivery);
                    mBtnLeft.setOnClickListener(view -> getPresenter().viewDelivery());

                    mBtnRight.setText(R.string.btn_sure_order);
                    mBtnRight.setOnClickListener(view -> sureOrder());
                    break;
                case OrderListActivity.STATE_UNCOMMENT:
                    mBtnLeft.setText(R.string.btn_view_delivery);
                    mBtnLeft.setOnClickListener(view -> getPresenter().viewDelivery());

                    mBtnRight.setText(R.string.btn_comment);
                    mBtnRight.setOnClickListener(view -> getPresenter().comment());
                    break;
                default:
                    mLayoutBtn.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void cancelOrder() {
        DialogFactory.createGenericDialog(
                this,
                R.string.dialog_cancel_order,
                (dialogInterface, which) -> {
                    getPresenter().cancelOrder();
                });
    }

    public void sureOrder() {
        DialogFactory.createGenericDialog(
                this,
                R.string.dialog_sure_order,
                (dialogInterface, which) -> {
                    getPresenter().sureOrder();
                });
    }

}
