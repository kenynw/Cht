package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.damenghai.chahuitong.module.mall.CartListPresenter;
import com.damenghai.chahuitong.widget.QuantityView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CartViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.cb_cart_select)
    CheckBox mCbSelect;

    @Bind(R.id.iv_cart_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_cart_name)
    TextView mTvName;

    @Bind(R.id.tv_cart_price)
    TextView mTvPrice;

    @Bind(R.id.btn_cart_minus)
    Button mBtnMinus;

    @Bind(R.id.tv_cart_num)
    TextView mTvNum;

    @Bind(R.id.btn_cart_plus)
    Button mBtnPlus;

    private CartListPresenter.CartAdapter mAdapter;

    public CartViewHolder(ViewGroup parent, CartListPresenter.CartAdapter adapter) {
        super(parent, R.layout.item_list_cart);
        ButterKnife.bind(this, itemView);

        mAdapter = adapter;
    }

    @Override
    public void setData(Goods goods) {
        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getContext().getString(R.string.text_rmb), goods.getGoods_price()));
        mTvNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (Integer.valueOf(charSequence.toString()) == 1) {
                    mBtnMinus.setEnabled(false);
                } else {
                    mBtnMinus.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mTvNum.setText(String.format("%s", goods.getGoods_num()));
        mBtnMinus.setOnClickListener(v -> changeCount(goods, -1));
        mBtnPlus.setOnClickListener(v -> changeCount(goods, +1));
//
//        mCountView.setCount(goods.getGoods_num());
//        mCountView.setOnCountChangedListener(count ->
//                GoodsModel.getInstance()
//                .editCartNum(goods.getCart_id(), count)
//                .subscribe(new ServiceResponse<Cart>() {
//                    @Override
//                    public void onNext(Cart cart) {
//                        goods.setGoods_sum(cart.getTotal_price());
//                        goods.setGoods_num(cart.getQuantity());
//                        EventBus.getDefault().post(cart);
//                    }
//                }));

        mCbSelect.setOnCheckedChangeListener((compoundButton, isCheck) -> mAdapter.checkedChange());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", goods.getGoods_id());
            getContext().startActivity(intent);
        });
    }

    private void changeCount(Goods goods, int value) {
        int num = Integer.valueOf(mTvNum.getText().toString()) + value;
        GoodsModel.getInstance()
                .editCartNum(goods.getCart_id(), num)
                .subscribe(new ServiceResponse<Cart>() {
                    @Override
                    public void onNext(Cart cart) {
                        mTvNum.setText(String.format("%s", num));
                        goods.setGoods_sum(cart.getTotal_price());
                        goods.setGoods_num(cart.getQuantity());
                        mAdapter.checkedChange();
                    }
                });
    }

    public boolean isChecked() {
        return mCbSelect.isChecked();
    }

    public void setCheck(boolean checked) {
        mCbSelect.setChecked(checked);
    }

}
