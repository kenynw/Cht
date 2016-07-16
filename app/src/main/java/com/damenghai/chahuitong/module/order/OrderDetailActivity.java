package com.damenghai.chahuitong.module.order;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.service.ServiceException;
import com.damenghai.chahuitong.module.address.AddressListActivity;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, Order> {

    @Bind(R.id.order_detail_tv_state)
    TextView mTvState;

    @Bind(R.id.tv_order_detail_consignee)
    TextView mTvConsignee;

    @Bind(R.id.tv_order_detail_address)
    TextView mTvAddress;

    @Bind(R.id.order_detail_tv_store)
    TextView mTvStore;

    @Bind(R.id.order_detail_goods_list)
    RecyclerView mLvGoods;

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
        setContentView(R.layout.order_activity_detail);
        setToolbarTitle(R.string.title_activity_order_detail);
        ButterKnife.bind(this);
        mLvGoods.setLayoutManager(new LinearLayoutManager(this));
        mLvGoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mLlPhone.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.text_phone_number)))));
    }

    @Override
    public void setData(Order order) {
        if (order != null) {
            mTvState.setText(order.getState_desc());
            mTvStore.setText(order.getStore_name());
            Address address;
            if ((address = order.getAddress_info()) != null) {
                mTvConsignee.setText(String.format(getString(R.string.text_consignee), address.getTrue_name(),address.getMob_phone()));
                mTvAddress.setText(String.format(getString(R.string.text_address), TextUtils.isEmpty(address.getArea_info()) ? "" : address.getArea_info(), address.getAddress()));
            }
            mTvFreight.setText(String.format(getString(R.string.text_rmb), order.getShipping_fee()));
            mTvTotal.setText(String.format(getString(R.string.text_rmb), order.getOrder_amount()));
            mTvOrderNo.setText(String.format(getString(R.string.label_order_num), order.getOrder_sn()));
            mTvPayNo.setText(String.format(getString(R.string.label_pay_num), order.getPay_sn()));
            mTvCreateTime.setText(String.format(getString(R.string.label_time_create), order.getAdd_time()));

            List<Goods> data = order.getExtend_order_goods();
            if (data != null) {
                mLvGoods.setAdapter(new OrderViewHolder.OrderGoodsListAdapter(OrderDetailActivity.this, data));
            }

            switch (order.getOrder_state()) {
                case OrderListActivity.STATE_UNPAID:
                    mBtnLeft.setOnClickListener(view -> getPresenter().cancelOrder());
                    mBtnRight.setOnClickListener(view -> getPresenter().payOrder());
                    break;
                case OrderListActivity.STATE_RECEIVE:
                    mBtnLeft.setText(R.string.btn_order_delivery);
                    mBtnLeft.setOnClickListener(view -> getPresenter().viewDelivery());

                    mBtnRight.setText(R.string.btn_order_sure);
                    mBtnRight.setOnClickListener(view -> getPresenter().sureOrder());
                    break;
                case OrderListActivity.STATE_UNCOMMENT:
                    mBtnLeft.setText(R.string.btn_order_delivery);
                    mBtnLeft.setOnClickListener(view -> getPresenter().viewDelivery());

                    mBtnRight.setText(R.string.btn_order_comment);
                    mBtnRight.setOnClickListener(view -> getPresenter().comment());
                    break;
                default:
                    mLayoutBtn.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e.getCause() instanceof ServiceException) {
            ServiceException s = (ServiceException) e.getCause();
            LUtils.toast(s.getMsg());
        } else {
            LUtils.toast(R.string.toast_network_error);
        }
    }
}
