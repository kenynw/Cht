package com.damenghai.chahuitong.view.mall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chaxin.paylibrary.pay.alipay.AlipayManager;
import com.chaxin.paylibrary.pay.wxpay.WxpayManager;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.Store;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.OrderInfo;
import com.damenghai.chahuitong.presenter.BuyPresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.web.WebViewActivity;
import com.damenghai.chahuitong.view.address.AddressListActivity;
import com.damenghai.chahuitong.widget.QuantityView;
import com.damenghai.chahuitong.widget.WrapHeightListView;
import com.damenghai.chahuitong.wxapi.WXPayEntryActivity;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

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

    private String mGoodsId;

    private String mBuyNum;

    private String mIfCart;

    private String mCartId;

    private String mTotal;

    private Address mAddress;

    private Voucher mVoucher;

    private List<Voucher> mVoucherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setToolbarTitle(R.string.title_activity_order);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//
//        mGoodsId = getIntent().getStringExtra("goods_id");
//        mBuyNum = getIntent().getStringExtra("buynum");
//
//        mIfCart = getIntent().getStringExtra("ifcart");
//        mCartId = getIntent().getStringExtra("cart_id");
    }

    @OnClick(R.id.tv_order_voucher)
    public void chooseVoucher() {
        if (mVoucherList == null) {
            LUtils.toast("没有可用的优惠券");
            return;
        }
        String[] items = new String[mVoucherList.size()];
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        for (int i=0; i<mVoucherList.size(); i++) {
            Voucher voucher = mVoucherList.get(i);
            items[i] = voucher.getVoucher_title();
        }
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Voucher voucher = mVoucherList.get(position);
                selectedVoucher(voucher);
            }
        });
        dialog.show();
    }

    // called when user change address
    public void onEventMainThread(Address address) {
        mAddress = address;
        mLayoutAddress.setVisibility(View.VISIBLE);
        mContainerAddress.setVisibility(View.GONE);
        mTvConsignee.setText(getResources().getString(R.string.label_consignee) + address.getTrue_name() + " " + address.getMob_phone());
        mTvAddress.setText(getResources().getString(R.string.label_address) + address.getArea_info() + " " + address.getAddress());
//        mPresenter.changeAddress();
    }

    public void selectedVoucher(Voucher voucher) {
        mVoucher = voucher;

        double total = Double.parseDouble(mTotal);
        double voucherPrice = Double.parseDouble(voucher.getVoucher_price());

        String result = (total - voucherPrice) + "";
        mTvOrderTotal.setText("￥" + result);
        mTvVoucher.setText(voucher.getVoucher_title());
    }

    public void setStore(Store store) {
        GoodsListAdapter adapter = new GoodsListAdapter(this, store.getGoods_list(), R.layout.item_list_order_goods);
        mListView.setAdapter(adapter);
        mTvFreight.setText(store.getFreight());
        mTvStoreCount.append(adapter.getCount() + "件)");

        mTvStoreName.setText(store.getStore_name());

        double total = Double.parseDouble(store.getStore_goods_total()) + Double.parseDouble(store.getFreight());
        mTotal = total + "";
        mTvStoreTotal.append(mTotal);
        mTvOrderTotal.append(mTotal);
        mBtnPay.setEnabled(true);

        if (total > 0) {
            mGroupPay.setVisibility(View.VISIBLE);
        } else {
            mGroupPay.setVisibility(View.GONE);
        }
    }

    public void setVoucher(List<Voucher> vouchers) {
        mVoucherList = vouchers;
    }

    @Override
    public void setData(OrderInfo orderInfo) {
        super.setData(orderInfo);

        LUtils.log("order info: " + new Gson().toJson(orderInfo));

        LUtils.log("store list: " + orderInfo.getStore_cart_list());

        if (orderInfo.getAddress_info() != null) {
            Address address = orderInfo.getAddress_info();
            mAddress = address;
            mLayoutAddress.setVisibility(View.VISIBLE);
            mContainerAddress.setVisibility(View.GONE);
            mTvConsignee.append(address.getTrue_name() + " " + address.getMob_phone());
            mTvAddress.append(address.getArea_info() + " " + address.getAddress());
        } else {
            mLayoutAddress.setVisibility(View.GONE);
            mContainerAddress.setVisibility(View.VISIBLE);
        }

        // 设置店铺信息

        // 设置优惠券

    }

    public void toChangeAddress(View view) {
        Intent intent = new Intent(BuyActivity.this, AddressListActivity.class);
        intent.putExtra("state", "choose");
        startActivity(intent);
    }

    public void toPay(View view) {
        view.setEnabled(false);
//        mPresenter.genPaySn();
    }

    public void startPay(Order order) {

        String title = getResources().getString(R.string.text_pay_title_prefix) + order.getOrder_sn();
        double price = Double.parseDouble(mTvOrderTotal.getText().toString().replace("￥", ""));

        final Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);

        switch (mGroupPay.getCheckedRadioButtonId()) {
            case R.id.order_rbtn_alipay :
                AlipayManager mManager = AlipayManager.getInstance(this);
                mManager.pay(title, title, price + "", order.getPay_sn(),
                        new AlipayManager.AlipayListener() {
                    @Override
                    public void onSuccess() {
                        bundle.putInt("resultCode", WXPayEntryActivity.RESULT_SUCCESS);
//                        openActivity(WXPayEntryActivity.class, bundle);
                    }

                    @Override
                    public void onConfirming() {
                        bundle.putInt("resultCode", WXPayEntryActivity.RESULT_FAIL);
//                        openActivity(WXPayEntryActivity.class, bundle);
                    }

                    @Override
                    public void onError() {
                        bundle.putInt("resultCode", WXPayEntryActivity.RESULT_FAIL);
//                        openActivity(WXPayEntryActivity.class, bundle);
                    }
                });
                break;
            case R.id.order_rbtn_wxpay :
//                openActivity(WXPayEntryActivity.class, bundle);
                WxpayManager manager = WxpayManager.getInstance(this);
                manager.pay(title, price, order.getPay_sn());
                finish();
                break;
            case R.id.order_rbtn_upmp :
//                String url = "http://www.chahuitong.com/mobile/index.php?act=member_payment&op=pay&key="
//                        + mPresenter.getKey()
//                        + "&pay_sn=" + order.getPay_sn() + "&payment_code=yinlian";
//                Bundle urlBundle = new Bundle();
//                urlBundle.putString("url", url);
//                openActivity(WebViewActivity.class, urlBundle);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.detach();
        EventBus.getDefault().unregister(this);
    }

    public String getCartId() {
        return TextUtils.isEmpty(mCartId) ? mGoodsId + "|" + mBuyNum : mCartId;
    }

    public String getIsCart() {
        return mIfCart;
    }

    public void setAddress(Address address) {
        mAddress = address;
        mLayoutAddress.setVisibility(View.VISIBLE);
        mContainerAddress.setVisibility(View.GONE);
        mTvConsignee.append(address.getTrue_name() + " " + address.getMob_phone());
        mTvAddress.append(address.getArea_info() + " " + address.getAddress());
    }

    public Address getAddress() {
        return mAddress;
    }

    public Voucher getVoucher() {
        return mVoucher;
    }

    private class GoodsListAdapter extends BaseListAdapter<Goods> {

        public GoodsListAdapter(Context context, List<Goods> data, int resId) {
            super(context, data, resId);
        }

        @Override
        public void convert(ViewHolder holder, Goods goods) {

            if(TextUtils.isEmpty(mCartId)) {
                holder.setVisibility(R.id.goods_count_view, View.VISIBLE)
                        .setVisibility(R.id.goods_tv_count, View.GONE);

                final Double price = Double.parseDouble(goods.getGoods_price());

                QuantityView viewCount = holder.getView(R.id.goods_count_view);
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

            holder.displayImage(R.id.goods_iv_thumb, goods.getGoods_image_url())
                    .setText(R.id.goods_tv_title, goods.getName())
                    .setText(R.id.goods_tv_price, "￥" + goods.getGoods_price());

        }

    }
}
