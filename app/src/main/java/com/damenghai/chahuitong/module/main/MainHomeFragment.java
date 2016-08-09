package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BannerPagerAdapter;
import com.damenghai.chahuitong.adapter.GalleyAdapter;
import com.damenghai.chahuitong.adapter.GoodsRecycleGridAdapter;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.module.special.BargainActivity;
import com.damenghai.chahuitong.module.special.SampleActivity;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.expansion.list.DividerGridItemDecoration;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.widget.GalleyLinearLayout;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 *
 */
@RequiresPresenter(MainHomePresenter.class)
public class MainHomeFragment extends BeamFragment<MainHomePresenter> {

    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.tv_toolbar_search)
    TextView mTvSearch;

    @Bind(R.id.home_refresh)
    SwipeRefreshLayout mRefreshLayout;

    @Bind(R.id.pager_home_banner)
    HeadViewPager mViewPager;

    @Bind(R.id.home_indicator)
    CirclePageIndicator mIndicator;

    @Bind(R.id.dv_home_sample)
    SimpleDraweeView mDvSample;

    @Bind(R.id.ll_home_sample)
    LinearLayout mLayoutSample;

    @Bind(R.id.dv_home_bargain)
    SimpleDraweeView mDvBargain;

    @Bind(R.id.ll_home_bargain)
    LinearLayout mLayoutBargain;

    @Bind(R.id.ll_home_commend)
    LinearLayout mLayoutCommend;

    @Bind(R.id.rcv_home_galley)
    RecyclerView mRvGalley;

    @Bind(R.id.gv_home_guess)
    RecyclerView mRvGuess;

    @Bind(R.id.btn_goods_list)
    TextView mBtnGoodsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_home, container, false);
        ButterKnife.bind(this, view);

        mRvGalley.setLayoutManager(new GalleyLinearLayout(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvGalley.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));

        mRvGuess.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvGuess.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        mRefreshLayout.setOnRefreshListener(getPresenter());
        mBtnGoodsList.setOnClickListener(v -> getPresenter().showGoodsList("goods_list"));
        mLayoutCommend.setOnClickListener(v -> getPresenter().showGoodsList("recommend_list"));

        initToolbar();

        return view;
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(item -> {
            getPresenter().showCart();
            return true;
        });
        mTvSearch.setOnClickListener(v -> startActivity(new Intent(getActivity(), SearchActivity.class)));
    }

    public void setData(Home home) {
        mViewPager.setAdapter(new BannerPagerAdapter(getActivity(), home.getAdds()));
        mIndicator.setViewPager(mViewPager);

        mDvSample.setImageURI(Uri.parse(home.getSample_image()));
        mDvBargain.setImageURI(Uri.parse(home.getXianshi().getGoods_image_url()));

        mRvGalley.setAdapter(new GalleyAdapter(getActivity(), home.getTasters_list()));
        mRvGuess.setAdapter(new GoodsRecycleGridAdapter(getActivity(), home.getGuess_list()));

        mLayoutSample.setOnClickListener(v -> startActivity(new Intent(getActivity(), SampleActivity.class)));
        mLayoutBargain.setOnClickListener(v -> startActivity(new Intent(getActivity(), BargainActivity.class)));
    }

    public void startRefresh() {
        mRefreshLayout.setProgressViewOffset(false, 0, 100);
        mRefreshLayout.setRefreshing(true);
    }

    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
