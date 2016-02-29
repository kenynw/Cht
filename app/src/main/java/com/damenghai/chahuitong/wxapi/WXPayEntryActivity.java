package com.damenghai.chahuitong.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaxin.paylibrary.pay.wxpay.Constants;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.main.MainActivity;
import com.damenghai.chahuitong.view.mall.PayActivity;
import com.damenghai.chahuitong.view.order.OrderDetailActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_CONFIRMING  = 1;
    public static final int RESULT_FAIL = -1;

    @Bind(R.id.tv_pay_result)
    TextView mTvResult;

    @Bind(R.id.btn_pay_view_order)
    Button mBtnOrder;

    @Bind(R.id.btn_pay_repay)
    Button mBtnRepay;

    @Bind(R.id.layout_pay_btn)
    LinearLayout mBtnLayout;

    private IWXAPI mWXApi;

    private Order mOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        setToolbarTitle(R.string.title_activity_pay_result);
        ButterKnife.bind(this);

        initWX();

        mOrder = (Order) getIntent().getSerializableExtra("order");

        setPayResult(getIntent().getIntExtra("resultCode", RESULT_CONFIRMING));
    }

    private void initWX() {
        mWXApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        mWXApi.handleIntent(getIntent(), this);
    }

    public void toHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void setPayResult(int resultCode) {
        LUtils.log("code: " + resultCode);
        switch (resultCode) {
            case RESULT_FAIL :
                mTvResult.setText(R.string.text_pay_fail);
                mBtnLayout.setVisibility(View.VISIBLE);
                mBtnOrder.setVisibility(View.GONE);
                mBtnRepay.setVisibility(View.VISIBLE);
                mBtnRepay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WXPayEntryActivity.this, PayActivity.class);
                        intent.putExtra("order", mOrder);
                        startActivity(intent);
                    }
                });
                break;
            case RESULT_SUCCESS :
                mTvResult.setText(R.string.text_pay_success);
                mBtnLayout.setVisibility(View.VISIBLE);
                mBtnRepay.setVisibility(View.GONE);
                mBtnOrder.setVisibility(View.VISIBLE);
                mBtnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WXPayEntryActivity.this, OrderDetailActivity.class);
                        intent.putExtra("order", mOrder);
                        startActivity(intent);
                    }
                });
                break;
            case RESULT_CONFIRMING :
                mTvResult.setText(R.string.text_pay_prepare);
                mBtnLayout.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onResp(final BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            LUtils.log(resp.getType() + "");
            if (resp.errCode == 0) {
                setPayResult(0);
            } else {
                setPayResult(-1);
            }
        }
    }

    @Override
    public void onReq(BaseReq req) {}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWXApi.handleIntent(intent, this);
    }

}