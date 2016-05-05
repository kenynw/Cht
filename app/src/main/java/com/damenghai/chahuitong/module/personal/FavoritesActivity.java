package com.damenghai.chahuitong.module.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsEditableViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FavoritesPresenter.class)
public class FavoritesActivity extends BaseListActivity<FavoritesPresenter> {

    @Bind(R.id.ll_favorites_action)
    LinearLayout mLlAction;

    @Bind(R.id.btn_favorites_all)
    Button mBtnAll;

    @Bind(R.id.btn_favorites_delete)
    Button mBtnDelete;

    @Bind(R.id.btn_favorites_shopping)
    Button mBtnShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_favorites);
        ButterKnife.bind(this);

        mBtnDelete.setOnClickListener(v -> getPresenter().delete());
        mBtnShopping.setOnClickListener(v -> startActivity(new Intent(this, GoodsListActivity.class).putExtra("op", "goods_list")));
        mBtnAll.setOnClickListener(v -> {
            for (Integer key : GoodsEditableViewHolder.mStates.keySet()) {
                if (!GoodsEditableViewHolder.mStates.get(key)) GoodsEditableViewHolder.mStates.put(key, true);
            }
        });
        getToolbar().setOnMenuItemClickListener(item -> {
            item.setVisible(false);
            switch (item.getItemId()) {
                case R.id.action_fav_edit:
                    mLlAction.setVisibility(View.VISIBLE);
                    GoodsEditableViewHolder.mEditable = true;
                    getToolbar().getMenu().getItem(1).setVisible(true);
                    break;
                case R.id.action_fav_done:
                    mLlAction.setVisibility(View.GONE);
                    GoodsEditableViewHolder.mEditable = false;
                    getToolbar().getMenu().getItem(0).setVisible(true);
                    break;
            }
            getPresenter().getAdapter().notifyDataSetChanged();
            return false;
        });
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsEditableViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_favorites;
    }

    @Override
    protected int getEmptyRes() {
        return R.layout.empty_favorites_list;
    }

    @Override
    protected int getLoadMoreRes() {
        return R.layout.default_footer_load_more;
    }

    @Override
    protected int getNoMoreRes() {
        return R.layout.default_footer_no_more;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GoodsEditableViewHolder.mEditable = false;
        GoodsEditableViewHolder.mStates.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

}
