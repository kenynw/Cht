package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.GoodsRecycleListAdapter;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.presenter.FavoritesPresenter;
import com.damenghai.chahuitong.module.special.BargainActivity;
import com.damenghai.chahuitong.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoritesActivity extends BaseActivity implements FavoritesMvpView {

    private final int MODE_NORMAL = 0x100;

    private final int MODE_EDIT = 0x200;

    private final int MODE_EMPTY = 0x300;

    @Bind(R.id.layout_favorites_empty)
    LinearLayout mLayoutEmpty;

    @Bind(R.id.rcv_favorites_list)
    RecyclerView mRecyclerView;

    @Bind(R.id.btn_favorites_shopping)
    Button mBtnShopping;

    private FavoritesPresenter mPresenter;

    private GoodsRecycleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_favorites);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setToolbarTitle(R.string.title_activity_favorites);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_fav_edit:
                        changeMode(MODE_EDIT);
                        break;
                    case R.id.action_fav_delete:
                        mPresenter.delete();
                        changeMode(MODE_NORMAL);
                        break;
                }
                return false;
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mPresenter = new FavoritesPresenter(this);
        mPresenter.attach(this);
        mPresenter.list();
    }

    private void changeMode(int modeType) {
        switch (modeType) {
            case MODE_NORMAL :
                getToolbar().getMenu().getItem(0).setVisible(true);
                getToolbar().getMenu().getItem(1).setVisible(false);
                mRecyclerView.setVisibility(View.VISIBLE);
                mLayoutEmpty.setVisibility(View.GONE);
                if (mAdapter != null) mAdapter.setEditable(false);
                break;
            case MODE_EDIT :
                getToolbar().getMenu().getItem(0).setVisible(false);
                getToolbar().getMenu().getItem(1).setVisible(true);
                if (mAdapter != null) mAdapter.setEditable(true);
                break;
            case MODE_EMPTY :
                getToolbar().getMenu().getItem(0).setVisible(false);
                getToolbar().getMenu().getItem(1).setVisible(false);
                mRecyclerView.setVisibility(View.GONE);
                mLayoutEmpty.setVisibility(View.VISIBLE);
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

    @Override
    public List<String> getFavIdList() {
        List<Goods> list = mAdapter.getList();
        Map<Integer, Boolean> map = mAdapter.getStateList();
        List<String> favIds = new ArrayList<>();
        for (Integer key : map.keySet()) {
            boolean value = map.get(key);
            if (value) {
                Goods goods = list.get(key);
                favIds.add(goods.getFav_id());
            }
        }

        return favIds;
    }

    @Override
    public void showList(List<Goods> list) {
        mAdapter = new GoodsRecycleListAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mLayoutEmpty.setVisibility(View.GONE);
        getToolbar().getMenu().getItem(0).setVisible(true);
    }

    @Override
    public void showEmpty() {
        changeMode(MODE_EMPTY);
    }

    @Override
    public void operateSuccess() {
        showShort(R.string.toast_operate_success);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
