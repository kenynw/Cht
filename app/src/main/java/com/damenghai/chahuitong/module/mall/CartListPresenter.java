package com.damenghai.chahuitong.module.mall;

import android.content.Context;
import android.content.Intent;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.CartViewHolder;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CartListPresenter extends BaseListActivityPresenter<CartListActivity, Goods> {

    @Override
    protected void onCreateView(CartListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        GoodsModel.getInstance().getCartList()
                .map(Cart::getCart_list)
                .unsafeSubscribe(new ServiceResponse<List<Goods>>() {
                    @Override
                    public void onError(Throwable e) {
                        getRefreshSubscriber().onError(e);
                    }

                    @Override
                    public void onNext(List<Goods> goodses) {
                        getAdapter().clearViewHolder();
                        getAdapter().clear();
                        getAdapter().addAll(goodses);
                    }
                });
    }

    @Override
    public DataAdapter createAdapter() {
        return new CartAdapter(getView());
    }

    public CartAdapter getAdapter() {
        return (CartAdapter) super.getAdapter();
    }

    public void showGoodsList() {
        Intent i = new Intent(getView(), GoodsListActivity.class);
        i.putExtra("op", "goods_list");
        getView().startActivity(i);
    }

    public void toPay() {
        if (getAdapter().getCheckedList().size() > 0) {
            StringBuilder cartID = new StringBuilder();
            for (int i = 0; i < getAdapter().getCheckedList().size(); i++) {
                Goods goods = getAdapter().getCheckedList().get(i);
                cartID.append(goods.getCart_id() + "|" + goods.getGoods_num());
                if (i != getAdapter().getCheckedList().size() - 1) cartID.append(",");
            }
            if (cartID.length() > 0) {
                getView().startActivity(BuyPresenter.getStartIntent(getView(), cartID.toString(), 1));
            }
        } else {
            LUtils.toast("您还没有选中商品哦");
        }
    }

    public void addFavorites() {
        if (getAdapter().getCheckedList().size() > 0) {
            String cartID = "";
            for (Goods goods : getAdapter().getCheckedList()) {
                cartID += goods.getCart_id() + ",";
            }
            GoodsModel.getInstance().moveFavorites(cartID).subscribe(new ServiceResponse<Boolean>() {
                @Override
                public void onNext(Boolean result) {
                    onRefresh();
                }
            });
        } else {
            LUtils.toast("您还没有选中商品哦");
        }
    }

    public void delete() {
        if (getAdapter().getCheckedList().size() > 0) {
            String cartID = "";
            for (Goods goods : getAdapter().getCheckedList()) {
                cartID += goods.getCart_id() + ",";
            }
            GoodsModel.getInstance().delCart(cartID).subscribe(new ServiceResponse<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    onRefresh();
                    LUtils.toast(R.string.toast_del_success);
                }
            });
        } else {
            LUtils.toast("您还没有选中商品哦");
        }
    }

    public class CartAdapter extends DataAdapter {

        private List<CartViewHolder> mVHList;

        public CartAdapter(Context context) {
            super(context);
            mVHList = new ArrayList<>(getCount());
        }

        public void registerViewHolder(CartViewHolder vh) {
            mVHList.add(vh);
        }

        public void clearViewHolder() {
            mVHList.clear();
        }

        public void checkedChange() {
            boolean allChecked = true;
            double total = 0;
            for (int i = 0; i < mVHList.size(); i++) {
                CartViewHolder vh = mVHList.get(i);
                allChecked = allChecked && vh.isChecked();
                if (vh.isChecked()) {
                    total += getItem(i).getGoods_sum();
                }
            }
            getView().setCheckAll(allChecked);
            getView().setTotal(total);
        }

        public void checkAll(boolean isCheckAll) {
            for (CartViewHolder vh : mVHList) {
                vh.setCheck(isCheckAll);
            }
        }

        public List<Goods> getCheckedList() {
            List<Goods> checkedList = new ArrayList<>();
            for (int i = 0; i < mVHList.size(); i++) {
                if (mVHList.get(i).isChecked()) checkedList.add(getItem(i));
            }
            return checkedList;
        }

    }

}
