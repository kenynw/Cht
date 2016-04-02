package com.damenghai.chahuitong.module.mall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chaxin.paylibrary.pay.alipay.AlipayManager;
import com.chaxin.paylibrary.pay.wxpay.WxpayManager;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.web.WebViewActivity;
import com.damenghai.chahuitong.wxapi.WXPayEntryActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayActivity extends BaseActivity {

    @Bind(R.id.group_pay_payment)
    RadioGroup mGroupPay;

    @Bind(R.id.tv_pay_total)
    TextView mTvTotal;

    private Order mOrder;

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setToolbarTitle(R.string.title_activity_pay_order);
        ButterKnife.bind(this);
        mOrder = (Order) getIntent().getSerializableExtra("order");
        mTvTotal.append(mOrder.getOrder_amount());
    }


    public void toPay(final View view) {
        if(mOrder == null || mOrder.getExtend_order_goods() == null) return;

        view.setEnabled(false);

        final Bundle bundle = new Bundle();
        bundle.putSerializable("order", mOrder);

        String title = getResources().getString(R.string.text_pay_title_prefix) + mOrder.getOrder_sn();
        String price = mOrder.getOrder_amount();

        switch (mGroupPay.getCheckedRadioButtonId()) {
            case R.id.btn_pay_ali :
                final ProgressDialog dialogPay = new ProgressDialog(this);
                dialogPay.setCanceledOnTouchOutside(false);
                dialogPay.setMessage(getResources().getString(R.string.toast_loading));
                dialogPay.show();

                AlipayManager.getInstance(this)
                        .pay(title, title, mOrder.getOrder_amount(), mOrder.getPay_sn(), new AlipayManager.AlipayListener() {
                            @Override
                            public void onSuccess() {
                                LUtils.toast(getString(R.string.toast_pay_success));
                                bundle.putInt("resultCode", WXPayEntryActivity.RESULT_SUCCESS);
                                openActivity(WXPayEntryActivity.class, bundle);
                                finish();
                            }

                            @Override
                            public void onConfirming() {
                                LUtils.toast(getString(R.string.toast_pay_comfirming));
                                view.setEnabled(true);
                                dialogPay.dismiss();
                            }

                            @Override
                            public void onError() {
                                LUtils.toast(getString(R.string.toast_pay_fail));
                                view.setEnabled(true);
                                dialogPay.dismiss();
                            }
                        });
                break;
            case R.id.btn_pay_wx:
                mView = view;
                WxpayManager wxpayManager = WxpayManager.getInstance(PayActivity.this);
                wxpayManager.pay(title, price, "SN" + mOrder.getPay_sn());
                startActivity(new Intent(this, WXPayEntryActivity.class));
                finish();
                break;
            case R.id.btn_pay_upmp:
                String url = "http://www.chahuitong.com/mobile/index.php?act=member_payment&op=pay&key="
                        + LUtils.getPreferences().getString("key", "")
                        + "&pay_sn=" + mOrder.getPay_sn().trim() + "&payment_code=yinlian";

                Intent intent = new Intent(PayActivity.this, WebViewActivity.class);
                intent.putExtra("url", url.trim());
                startActivity(intent);
                finish();
                break;
        }
    }

}
