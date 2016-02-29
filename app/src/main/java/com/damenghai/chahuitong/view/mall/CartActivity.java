package com.damenghai.chahuitong.view.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.CartListAdapter;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.presenter.CartPresenter;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.special.BargainActivity;
import com.damenghai.chahuitong.view.user.LoginActivity;
import com.damenghai.chahuitong.widget.DividerItemDecoration;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements CartMvpView {

    private final int MODE_NORMAL = 0x100;

    private final int MODE_EDIT = 0x200;

    private final int MODE_EMPTY = 0x300;

    @Bind(R.id.layout_cart_empty)
    LinearLayout mLayoutEmpty;      // 购物车为空

    @Bind(R.id.btn_cart_shopping)
    Button mBtnShopping;

    @Bind(R.id.cart_list)
    RecyclerView mRecyclerView;     // 列表

    @Bind(R.id.cart_cb_all)
    CheckBox mCheckAll;             // 全选

    @Bind(R.id.layout_cart_delete)
    LinearLayout mLayoutDelete;     // 编辑模式

    @Bind(R.id.layout_cart_total)
    LinearLayout mLayoutTotal;      // 正常模式

    @Bind(R.id.cart_tv_total)
    TextView mTvTotal;              // 总金额

    @Bind(R.id.btn_cart_balance)
    Button mBtnBalance;

    private CartListAdapter mAdapter;

    private CartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setToolbarTitle(R.string.tab_home_cart);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        changeMode(MODE_EDIT);
                        break;
                    case R.id.action_done:
                        changeMode(MODE_NORMAL);
                        mPresenter.editQuantity();
                        break;
                }
                return true;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mPresenter = new CartPresenter(this);
        mPresenter.attach(this);
        mPresenter.loadData();
    }

    private void changeMode(int mode) {
        switch (mode) {
            case MODE_NORMAL :
                getToolbar().getMenu().getItem(0).setVisible(true);
                getToolbar().getMenu().getItem(1).setVisible(false);
                mRecyclerView.setVisibility(View.VISIBLE);
                mLayoutEmpty.setVisibility(View.GONE);
                mCheckAll.setEnabled(true);
                mLayoutDelete.setVisibility(View.GONE);
                mLayoutTotal.setVisibility(View.VISIBLE);
                if (mAdapter != null) mAdapter.setEditable(false);
                break;
            case MODE_EDIT :
                getToolbar().getMenu().getItem(0).setVisible(false);
                getToolbar().getMenu().getItem(1).setVisible(true);
                mCheckAll.setEnabled(true);
                mLayoutDelete.setVisibility(View.VISIBLE);
                mLayoutTotal.setVisibility(View.GONE);
                if (mAdapter != null) mAdapter.setEditable(true);
                break;
            case MODE_EMPTY :
                getToolbar().getMenu().getItem(0).setVisible(false);
                getToolbar().getMenu().getItem(1).setVisible(false);
                mCheckAll.setEnabled(false);
                mBtnBalance.setEnabled(false);
                mLayoutDelete.setVisibility(View.GONE);
                mLayoutTotal.setVisibility(View.VISIBLE);
                mLayoutEmpty.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mTvTotal.setText("￥0.00");
                mBtnShopping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(BargainActivity.class);
                        finish();
                    }
                });
                break;
        }
    }

    private void changeModeToEdit(boolean isEdit) {
        if (isEdit) {
            mLayoutDelete.setVisibility(View.VISIBLE);
            mLayoutTotal.setVisibility(View.GONE);
        } else {
            mLayoutDelete.setVisibility(View.GONE);
            mLayoutTotal.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.cart_cb_all)
    public void toCheckAll(View view) {
        Map<Integer, Boolean> map = mAdapter.getStates();
        for (Integer key : map.keySet()) {
            map.put(key, mCheckAll.isChecked());
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_cart_delete)
    public void toDelete() {
        mPresenter.delete();
    }

    @OnClick(R.id.btn_cart_favorites)
    public void toFavorites() {
        mPresenter.addFavorites();
    }

    @OnClick(R.id.btn_cart_balance)
    public void toBalance() {
        List<Goods> data = mAdapter.getCheckedItems();
        if (data != null && data.size() > 0) {
            StringBuilder cartId = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                Goods goods = data.get(i);
                cartId.append(goods.getCart_id() + "|" + goods.getGoods_num());
                if (i != data.size() - 1) cartId.append(",");
            }

            Intent intent = new Intent(this, BuyActivity.class);
            intent.putExtra("ifcart", "1");
            intent.putExtra("cart_id", cartId.toString());
            startActivity(intent);
        }
    }
    @OnClick(R.id.btn_cart_shopping)
    public void shopping() {
        openActivity(BargainActivity.class);
    }

    @Override
    public void setList(List<Goods> list) {
        mAdapter = new CartListAdapter(CartActivity.this, list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnSelectedItemChangedListener(new CartListAdapter.OnSelectedItemChangedListener() {
            @Override
            public void onSelectedItemChanged(List<Goods> selectedList) {
                if (selectedList.size() == mAdapter.getItemCount()) mCheckAll.setChecked(true);
                else mCheckAll.setChecked(false);

                double total = 0;
                for (Goods goods : selectedList) {
                    double price = Double.parseDouble(goods.getGoods_price());
                    total += price;
                }
                mTvTotal.setText("￥" + total);
            }
        });
        changeMode(MODE_NORMAL);
    }

    @Override
    public void setTotal(String total) {
        mTvTotal.setText("￥" + total);
    }

    @Override
    public void showEmpty() {
        changeMode(MODE_EMPTY);
    }

    @Override
    public void showLogin() {
        openActivity(LoginActivity.class);
        finish();
    }

    @Override
    public String getQuantity() {
        return null;
    }

    @Override
    public CartListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_list, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

}
