package com.damenghai.chahuitong.module.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.GoodsOrderAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Store;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.module.personal.AddressEditActivity;
import com.damenghai.chahuitong.module.personal.AddressListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BuyPresenter.class)
public class BuyActivity extends BaseDataActivity<BuyPresenter, OrderInfo> {

    @Bind(R.id.ll_buy_address)
    LinearLayout mLayoutAddress;

    @Bind(R.id.btn_buy_address)
    Button mBtnAddress;

    @Bind(R.id.tv_address_name)
    TextView mTvAddressName;

    @Bind(R.id.tv_address_mobile)
    TextView mTvAddressMobile;

    @Bind(R.id.tv_address_detail)
    TextView mTvAddressInfo;

    @Bind(R.id.rcv_buy_goods)
    RecyclerView mRcvGoods;

    @Bind(R.id.tv_buy_freight)
    TextView mTvFreight;

    @Bind(R.id.tv_buy_total)
    TextView mTvTotal;

    @Bind(R.id.btn_buy_voucher)
    Button mBtnVoucher;

    @Bind(R.id.tv_buy_voucher_none)
    TextView mTvVoucherNone;

    @Bind(R.id.group_buy_payments)
    RadioGroup mGroupPay;

    @Bind(R.id.tv_buy_pay_total)
    TextView mTvOrderTotal;

    @Bind(R.id.btn_buy_pay)
    Button mBtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall_activity_buy);
        setToolbarTitle(R.string.title_activity_order);
        ButterKnife.bind(this);

        mRcvGoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRcvGoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void setData(OrderInfo orderInfo) {
        setAddress(orderInfo.getAddress_info());
        Store store = orderInfo.getStore_cart_list().get(0);
        mRcvGoods.setAdapter(new GoodsOrderAdapter(this, store.getGoods_list()));
        mTvTotal.setText(String.format(getString(R.string.text_rmb), store.getStore_goods_total()));
        mTvOrderTotal.setText(String.format(getString(R.string.text_rmb), store.getStore_goods_total()));
        if (!store.isFreight()) {
            double freight = 0.00;
            for (Goods goods : store.getGoods_list()) {
                freight += goods.getGoods_freight();
            }
            mTvFreight.setText(String.format("%s", freight));
        }

        if (store.getStore_voucher_list() == null || store.getStore_voucher_list().size() <= 0) {
            mTvVoucherNone.setVisibility(View.VISIBLE);
        } else {
            mBtnVoucher.setOnClickListener(v -> getPresenter().showVoucher(store.getStore_voucher_list()));
        }
        mBtnPay.setOnClickListener(v -> getPresenter().addOrder());
    }

    public void setAddress(Address address) {
        if (address != null) {
            mTvAddressName.setText(address.getTrue_name());
            mTvAddressMobile.setText(address.getMob_phone());
            mTvAddressInfo.setText(address.getAddress());
            mLayoutAddress.setOnClickListener(v -> getPresenter().showAddress());
        } else {
            mLayoutAddress.setVisibility(View.GONE);
            mBtnAddress.setVisibility(View.VISIBLE);
            mBtnAddress.setOnClickListener(v -> getPresenter().showAddress());
        }
    }

    public void setVoucher(Voucher voucher) {
        mBtnVoucher.setText(voucher.getVoucher_title());
        double total = Double.valueOf(mTvOrderTotal.getText().toString().replace("￥", ""));
        if (total > 0) {
            mTvOrderTotal.setText(String.format(getString(R.string.text_rmb), total - voucher.getVoucher_price()));
        }
    }

    public int getPayViewId() {
        return mGroupPay.getCheckedRadioButtonId();
    }

    public String getTotal() {
        return mTvOrderTotal.getText().toString().replace("￥", "");
//        return "0.01";
    }

}
