package com.damenghai.chahuitong.view.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.GoodsListAdapter;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.view.mall.PayActivity;
import com.damenghai.chahuitong.view.web.WebViewActivity;
import com.damenghai.chahuitong.widget.WrapHeightListView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity {

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

    private Order mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setToolbarTitle(R.string.title_activity_order_detail);
        ButterKnife.bind(this);

        mOrder = (Order) getIntent().getSerializableExtra("order");

        initView();
    }

    private void initView() {
        if (mOrder != null) {
            mTvState.setText(mOrder.getState_desc());
            mTvStore.setText(mOrder.getStore_name());
            mTvFreight.setText(mOrder.getShipping_fee());
            mTvTotal.append(mOrder.getOrder_amount());
            mTvOrderNo.append(mOrder.getOrder_sn());
            mTvPayNo.append(mOrder.getPay_sn());
            mTvCreateTime.append(DateUtils.getDateTime(mOrder.getAdd_time()));
            ArrayList<Goods> data = mOrder.getExtend_order_goods();
            if (data != null) {
                GoodsListAdapter adapter = new GoodsListAdapter(this, data, R.layout.item_list_order_goods);
                mLvGoods.setAdapter(adapter);
                mLvGoods.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(OrderDetailActivity.this, GoodsDetailActivity.class);
                    intent.putExtra("goods_id", mOrder.getExtend_order_goods().get(position).getGoods_id());
                    startActivity(intent);
                });
            }

            switch (mOrder.getOrder_state()) {
                case OrderListActivity.STATE_UNPAID :
                    mBtnLeft.setOnClickListener(view -> cancelOrder());
                    mBtnRight.setOnClickListener(view -> payOrder());
                    break;
                case OrderListActivity.STATE_RECEIVE :
                    mBtnLeft.setText(R.string.btn_view_delivery);
                    mBtnLeft.setOnClickListener(view -> viewDelivery());

                    mBtnRight.setText(R.string.btn_sure_order);
                    mBtnRight.setOnClickListener(view -> sureOrder());
                    break;
                case OrderListActivity.STATE_UNCOMMENT :
                    mBtnLeft.setText(R.string.btn_view_delivery);
                    mBtnLeft.setOnClickListener(view -> viewDelivery());

                    mBtnRight.setText(R.string.btn_comment);
                    mBtnRight.setOnClickListener(view -> comment());
                    break;
                default:
                    mLayoutBtn.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void cancelOrder() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.dialog_cancel_order)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, (dialogInterface, i) -> {
                })
                .show();

    }

    public void sureOrder() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.dialog_sure_order)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        OrderApi.sureOrder(OrderDetailActivity.this, mOrder.getOrder_id(), new VolleyRequest() {
//                            @Override
//                            public void onSuccess(String response) {
//                                super.onSuccess(response);
//                                try {
//                                    JSONObject object = new JSONObject(response);
//                                    if (object.has("error")) {
//                                        T.showShort(OrderDetailActivity.this, object.getString("error"));
//                                    } else {
//                                        T.showShort(OrderDetailActivity.this, R.string.toast_sure_success);
//                                        EventBus.getDefault().post(mOrder);
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                    }
                })
                .show();
    }

    public void viewDelivery() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", "http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=" + mOrder.getOrder_id());
        startActivity(intent);
    }

    public void comment() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", "http://www.chahuitong.com/wap/index.php/Home/Index/pingjiaorder/oid/" + mOrder.getOrder_id());
        startActivity(intent);
    }

    public void payOrder() {
        Intent intent = new Intent(this, PayActivity.class);
        intent.putExtra("order", mOrder);
        startActivity(intent);
    }

    public void toCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:05925990900"));
        startActivity(intent);
    }
}
