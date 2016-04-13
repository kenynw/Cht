package com.damenghai.chahuitong.module.special;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Recommend;
import com.damenghai.chahuitong.presenter.ValuerPresenter;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.widget.ExpandTabView;
import com.damenghai.chahuitong.widget.ExpandTabView.OnItemSelectedListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ValuerActivity extends BaseActivity implements ValuerListMvp, OnItemSelectedListener,
        OnLastItemVisibleListener, OnRefreshListener<ListView> {

    @Bind(R.id.valuer_lv)
    PullToRefreshListView mPlv;

    @Bind(R.id.valuator_tab)
    ExpandTabView mTabView;

    @Bind(R.id.tv_valuer_empty)
    TextView mTvEmpty;

    private String mCateSort;

    private int mPriceSort;

    private int mSalesSort;

    private int mClickSort;

    private ListAdapter mAdapter;

    private List<Category> mCategoryList;

    private ValuerPresenter mPresenter = new ValuerPresenter();

    private int mCurPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_activity_valuer);
        setToolbarTitle(R.string.title_valuator_recommend);
        ButterKnife.bind(this);

        init();

        mCurPage = 1;
        mPresenter.attach(this);
        mPresenter.loadList();
    }

    public ValuerPresenter getPresenter() {
        return new ValuerPresenter();
    }

    private void init() {
        mTabView.setOnItemSelectedListener(this);
        mPlv.setOnRefreshListener(this);
        mPlv.setOnLastItemVisibleListener(this);
    }

    @Override
    public void showRecommendList(List<Recommend> list) {
        if (mAdapter == null) {
            mAdapter = new ListAdapter(ValuerActivity.this, list, R.layout.item_list_valuer);
            mPlv.setAdapter(mAdapter);
        } else {
            mAdapter.addList(list);
        }

        mPlv.setVisibility(View.VISIBLE);
        mTvEmpty.setVisibility(View.GONE);
    }

    @Override
    public void setCategory(List<Category> list) {
        String[] results = new String[list.size()];
        for (int i=0; i<list.size(); i++) {
            results[i] = list.get(i).getGc_name();
        }
        mTabView.setData(0, results);

        mCategoryList = list;
    }

    @Override
    public int getCurPage() {
        return mCurPage;
    }

    @Override
    public String getCateSort() {
        return mCateSort;
    }

    @Override
    public int getPriceSort() {
        return mPriceSort;
    }

    @Override
    public int getSalesSort() {
        return mSalesSort;
    }

    @Override
    public int getClickSort() {
        return mClickSort;
    }

    @Override
    public void clear() {
        if (mAdapter != null) mAdapter.clear();
    }

    @Override
    public void showLoading() {
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
        mPlv.setVisibility(View.VISIBLE);
        if (mPlv.isRefreshing()) mPlv.onRefreshComplete();
    }

    @Override
    public void showError(String message) {
        mPlv.setVisibility(View.GONE);
        mTvEmpty.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void onBackPressed() {
        if (!mTabView.dismissMenu()) {
            finish();
        }
    }

    @Override
    public void onItemSelected(int position, int selected) {

        switch (position) {
            case 0 :
                if (mCategoryList != null) mCateSort = mCategoryList.get(selected).getGc_id() + "";
                break;
            case 1 :
                mPriceSort = selected;
                break;
            case 2 :
                mSalesSort = selected;
                break;
            case 3 :
                mClickSort = selected;
                break;
        }

        mPlv.setRefreshing();
    }


    @Override
    public void onLastItemVisible() {
        mCurPage += 1;
        mPresenter.loadList();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        mCurPage = 1;
        mPresenter.loadList();
    }

    private class ListAdapter extends BaseListAdapter<Recommend> {

        public ListAdapter(Context context, List<Recommend> data, int resId) {
            super(context, data, resId);
        }

        @Override
        public void convert(ViewHolder holder, final Recommend goods) {
            String scoreLabel = mContext.getResources().getString(R.string.label_recommend_score);
            SpannableString spanText = new SpannableString(scoreLabel + goods.getRecommend_score() + "分");
            spanText.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
                    scoreLabel.length(), spanText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            SimpleDraweeView dvImage = holder.getView(R.id.dv_goods_thumb);
            dvImage.setImageURI(Uri.parse(goods.getGoods_image_url()));

            holder.setText(R.id.tv_goods_name, goods.getGoods_name())
                    .setText(R.id.item_valuator_price, "￥" + goods.getGoods_price())
                    .setText(R.id.item_valuator_score, spanText);

            holder.getConvertView().setOnClickListener(v -> {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                mContext.startActivity(intent);
            });
        }
    }

}
