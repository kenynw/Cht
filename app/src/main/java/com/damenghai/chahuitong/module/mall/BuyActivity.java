package com.damenghai.chahuitong.module.mall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Store;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.module.address.AddressListActivity;
import com.damenghai.chahuitong.widget.QuantityView;
import com.damenghai.chahuitong.widget.WrapHeightListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BuyPresenter.class)
public class BuyActivity extends BaseDataActivity<BuyPresenter, OrderInfo> {

    @Bind(R.id.order_address_container)
    FrameLayout mContainerAddress;

    @Bind(R.id.order_ll_address)
    LinearLayout mLayoutAddress;

    @Bind(R.id.order_tv_consignee)
    TextView mTvConsignee;

    @Bind(R.id.order_tv_address)
    TextView mTvAddress;

    @Bind(R.id.tv_order_voucher)
    TextView mTvVoucher;

    @Bind(R.id.store_tv_name)
    TextView mTvStoreName;

    @Bind(R.id.store_tv_total_count)
    TextView mTvStoreCount;

    @Bind(R.id.store_tv_total)
    TextView mTvStoreTotal;

    @Bind(R.id.order_group_pay)
    RadioGroup mGroupPay;

    @Bind(R.id.store_lv_goods)
    WrapHeightListView mListView;

    @Bind(R.id.tv_order_freight)
    TextView mTvFreight;

    @Bind(R.id.order_tv_pay_total)
    TextView mTvOrderTotal;

    @Bind(R.id.btn_buy_pay)
    Button mBtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setToolbarTitle(R.string.title_activity_order);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(OrderInfo orderInfo) {
        super.setData(orderInfo);
        setAddress(orderInfo.getAddress_info());
        setStore(orderInfo.getStore_cart_list().get(0));
        mBtnPay.setOnClickListener(v -> {
            v.setEnabled(false);
            getPresenter().pay();
        });
    }

    public void setAddress(Address address) {
        if (address != null) {
            mContainerAddress.setVisibility(View.GONE);
            mTvConsignee.setText(getString(R.string.label_consignee) + address.getTrue_name() + " " + address.getMob_phone());
            mTvAddress.setText(getString(R.string.label_address) + address.getArea_info() + " " + address.getAddress());
            mLayoutAddress.setVisibility(View.VISIBLE);
            mLayoutAddress.setOnClickListener(v -> {
                Intent intent = new Intent(BuyActivity.this, AddressListActivity.class);
                intent.putExtra("state", "choose");
                startActivity(intent);
            });
        } else {
            mLayoutAddress.setVisibility(View.GONE);
            mContainerAddress.setVisibility(View.VISIBLE);
        }
    }

    private void setStore(Store store) {
//        if (store.isFreight()) mTvFreight.setText("计算运费");
        mTvStoreCount.append(store.getGoods_list().size() + "件)");
        mTvStoreName.setText(store.getStore_name());
        mListView.setAdapter(new GoodsListAdapter(this, store.getGoods_list(), R.layout.item_list_order_goods));

        String total = store.getStore_goods_total() + "";
        mTvStoreTotal.append(total);
        mTvOrderTotal.append(total);
        mBtnPay.setEnabled(true);
        setVoucher(store.getStore_voucher_list(), store.getStore_goods_total());

        if (store.getStore_goods_total() > 0) {
            mGroupPay.setVisibility(View.VISIBLE);
        } else {
            mGroupPay.setVisibility(View.GONE);
        }
    }

    public void setVoucher(List<Voucher> vouchers, double total) {
        if (vouchers == null) {
            mTvVoucher.setText(R.string.text_voucher_none);
            mTvVoucher.setEnabled(false);
            return;
        }

        String[] items = new String[vouchers.size()];
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        for (int i=0; i<vouchers.size(); i++) {
            Voucher voucher = vouchers.get(i);
            items[i] = voucher.getVoucher_title();
        }
        dialog.setItems(items, (dialog1, position) -> {
            Voucher voucher = vouchers.get(position);

            double voucherPrice = Double.parseDouble(voucher.getVoucher_price());
            String result = (total - voucherPrice) + "";
            mTvOrderTotal.setText("￥" + result);
            mTvVoucher.setText(voucher.getVoucher_title());

            getPresenter().setVoucher(voucher);
        });
        mTvVoucher.setOnClickListener(v -> dialog.show());
    }

    public String genCartId() {
        return ((GoodsListAdapter) mListView.getAdapter()).getCartId();
    }

    public int getPayViewId() {
        return mGroupPay.getCheckedRadioButtonId();
    }

    public String getTotal() {
        return mTvOrderTotal.getText().toString().replace("￥", "");
//        return "0.01";
    }

    private class GoodsListAdapter extends BaseListAdapter<Goods> {

        private List<QuantityView> mCountViews;

        public GoodsListAdapter(Context context, List<Goods> data, int resId) {
            super(context, data, resId);
            mCountViews = new ArrayList<>();
        }

        public String getCartId() {
            StringBuilder cartId = new StringBuilder();
            for (int i=0; i<getCount(); i++) {
                cartId.append(mData.get(i).getGoods_id() + "|" + mCountViews.get(i).getCount());
            }
            return cartId.toString();
        }

        @Override
        public void convert(ViewHolder holder, Goods goods) {

            if(getIntent().getStringExtra(BuyPresenter.EXTRA_IF_CART).equals("0")) {
                holder.setVisibility(R.id.goods_count_view, View.VISIBLE)
                        .setVisibility(R.id.goods_tv_count, View.GONE);

                final Double price = Double.parseDouble(goods.getGoods_price());

                QuantityView viewCount = holder.getView(R.id.goods_count_view);
                mCountViews.add(viewCount);
                if (price > 0) {
                    viewCount.setCount(goods.getGoods_num());
                    viewCount.setOnCountChangedListener(new QuantityView.OnCountChangedListener() {
                        @Override
                        public void countChanged(double count) {
                            double total = count * price;
                            BigDecimal bd = new BigDecimal(total);
                            double result = bd.setScale(2, bd.ROUND_HALF_UP).doubleValue();
                            mTvStoreCount.setText("共" + (int) count + "件商品：");
                            mTvStoreTotal.setText("￥" + result);
                            mTvOrderTotal.setText("￥" + result);
                        }
                    });
                } else {
                    viewCount.setVisibility(View.GONE);
                }
            } else {
                holder.setText(R.id.goods_tv_count, "x" + goods.getGoods_num())
                        .setVisibility(R.id.goods_count_view, View.GONE)
                        .setVisibility(R.id.goods_tv_count, View.VISIBLE);
            }

            holder.displayImage(R.id.dv_goods_thumb, goods.getGoods_image_url())
                    .setText(R.id.tv_goods_name, goods.getGoods_name())
                    .setText(R.id.tv_goods_price, "￥" + goods.getGoods_price());

        }

    }
}
