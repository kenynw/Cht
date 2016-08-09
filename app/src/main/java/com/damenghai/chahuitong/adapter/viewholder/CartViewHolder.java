package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
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

    @Bind(R.id.count_view_cart_count)
    QuantityView mCountView;

    @Bind(R.id.tv_cart_price)
    TextView mTvPrice;

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
        mCountView.setCount(goods.getGoods_num());
        mCountView.setOnCountChangedListener(count -> GoodsModel.getInstance()
                .editCartNum(goods.getCart_id(), count)
                .subscribe(new ServiceResponse<Cart>() {
                    @Override
                    public void onNext(Cart cart) {
                        goods.setGoods_sum(cart.getTotal_price());
                        goods.setGoods_num(cart.getQuantity());
                        EventBus.getDefault().post(cart);
                    }
                }));

        mCbSelect.setOnCheckedChangeListener((compoundButton, isCheck) -> mAdapter.checkedChange());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", goods.getGoods_id());
            getContext().startActivity(intent);
        });
    }

    public boolean isChecked() {
        return mCbSelect.isChecked();
    }

    public void setCheck(boolean checked) {
        mCbSelect.setChecked(checked);
    }

}
