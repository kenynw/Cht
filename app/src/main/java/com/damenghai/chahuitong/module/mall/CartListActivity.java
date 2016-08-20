package com.damenghai.chahuitong.module.mall;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.CartViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(CartListPresenter.class)
public class CartListActivity extends BaseListActivity<CartListPresenter> {

    @Bind(R.id.btn_cart_shopping)
    Button mBtnShopping;

    @Bind(R.id.cart_cb_all)
    CheckBox mCheckAll;

    @Bind(R.id.layout_cart_delete)
    LinearLayout mLayoutDelete;

    @Bind(R.id.layout_cart_total)
    LinearLayout mLayoutTotal;

    @Bind(R.id.cart_tv_total)
    TextView mTvTotal;

    @Bind(R.id.btn_cart_favorites)
    Button mBtnFav;

    @Bind(R.id.btn_cart_delete)
    Button mBtnDelete;

    @Bind(R.id.btn_cart_pay)
    Button mBtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_cart);
        ButterKnife.bind(this);

        mBtnFav.setOnClickListener(v -> getPresenter().addFavorites());
        mBtnDelete.setOnClickListener(v -> getPresenter().delete());
        mBtnPay.setOnClickListener(v -> getPresenter().toPay());
        mBtnShopping.setOnClickListener(v -> getPresenter().showGoodsList());
        mCheckAll.setOnClickListener(v -> getPresenter().getAdapter().checkAll(mCheckAll.isChecked()));
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        CartListPresenter.CartAdapter adapter = getPresenter().getAdapter();
        CartViewHolder vh = new CartViewHolder(parent, adapter);
        adapter.registerViewHolder(vh);
        return vh;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_activity_cart;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerEmptyRes(R.layout.empty_cart_list)
                .setLoadMoreAble(false)
                .setRefreshAble(false)
                .setNoMoreAble(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setVisible(false);
        switch (item.getItemId()) {
            case R.id.action_edit :
                getToolbar().getMenu().findItem(R.id.action_done).setVisible(true);
                mLayoutDelete.setVisibility(View.VISIBLE);
                mLayoutTotal.setVisibility(View.GONE);
                break;
            case R.id.action_done :
                getToolbar().getMenu().findItem(R.id.action_edit).setVisible(true);
                mLayoutDelete.setVisibility(View.GONE);
                mLayoutTotal.setVisibility(View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setTotal(double total) {
        mTvTotal.setText(String.format(getString(R.string.text_rmb), total));
    }

    public void setCheckAll(boolean checkAll) {
        mCheckAll.setChecked(checkAll);
    }

}
