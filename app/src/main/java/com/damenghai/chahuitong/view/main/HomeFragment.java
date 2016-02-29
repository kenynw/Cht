package com.damenghai.chahuitong.view.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BannerPagerAdapter;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.GalleyAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.model.bean.Banner;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.model.bean.Recommend;
import com.damenghai.chahuitong.presenter.HomePresenter;
import com.damenghai.chahuitong.view.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.view.special.BargainActivity;
import com.damenghai.chahuitong.view.special.SampleActivity;
import com.damenghai.chahuitong.utils.T;
import com.damenghai.chahuitong.view.special.ValuerActivity;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.damenghai.chahuitong.widget.WrapHeightGridView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends BaseFragment implements HomeMvpView, View.OnTouchListener {

    private static final String KEY_ITEM_HEIGHT = "HomeFragment:ItemHeight";

    @Bind(R.id.sv_home_layout)
    ScrollView mSvLayout;

    @Bind(R.id.pager_home_banner)
    HeadViewPager mViewPager;

    @Bind(R.id.home_indicator)
    CirclePageIndicator mIndicator;

    @Bind(R.id.iv_home_free)
    SimpleDraweeView mIvFree;

    @Bind(R.id.iv_home_bargain)
    SimpleDraweeView mIvBargain;

    @Bind(R.id.rcv_home_galley)
    RecyclerView mRcvGalley;

    @Bind(R.id.gv_home_guess)
    WrapHeightGridView mGvGuess;

    private HomePresenter mHomePresenter;

    private int mCurPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomePresenter = new HomePresenter(mContext);
        mHomePresenter.attach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_home, container, false);
        ButterKnife.bind(this, view);

        mRcvGalley.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mSvLayout.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCurPage = 1;
        mHomePresenter.loadHomeData();
    }

    @Override
    public int getCurPage() {
        return mCurPage;
    }

    @Override
    public void showBanner(List<Banner> banners) {
        if (banners != null) {
            BannerPagerAdapter adapter = new BannerPagerAdapter(mContext, banners);
            mViewPager.setAdapter(adapter);
            mIndicator.setViewPager(mViewPager);
        }
    }

    @Override
    public void showSpecial(Image sampleImage, Bargain goods) {
        mIvFree.setImageURI(Uri.parse(sampleImage.getBmiddle_pic()));
        mIvBargain.setImageURI(Uri.parse(goods.getGoods_image().getBmiddle_pic()));
    }

    @Override
    public void showRecommend(List<Recommend> goodsList) {
        mRcvGalley.setAdapter(new GalleyAdapter(mContext, goodsList));
    }

    @Override
    public void showRecommendForYou(List<Goods> goodsList) {
        if (mGvGuess.getAdapter() != null && mGvGuess.getAdapter() instanceof GoodsListAdapter) {
            ((GoodsListAdapter) mGvGuess.getAdapter()).addList(goodsList);
        } else {
            mGvGuess.setAdapter(new GoodsListAdapter(mContext, goodsList, R.layout.item_list_goods));
        }
    }

    @Override
    public void showError(String message) {
        T.showShort(mContext, message);
    }

    @OnClick(R.id.rl_home_free)
    void sampleList() {
        openActivity(SampleActivity.class);
    }

    @OnClick(R.id.rl_home_discount)
    void discountList() {
        openActivity(BargainActivity.class);
    }

    @OnClick({R.id.iv_home_recommend, R.id.ll_valuator_layout})
    void valuatorList() {
        openActivity(ValuerActivity.class);
    }

    @OnClick(R.id.ll_recommend_layout)
    void recommendList() {

    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putInt(KEY_ITEM_HEIGHT, mRcvGalley.getHeight());
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        int height = savedInstanceState.getInt(KEY_ITEM_HEIGHT);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mRcvGalley.getLayoutParams();
        params.height = height;
        mRcvGalley.setLayoutParams(params);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHomePresenter.detach();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int layoutHeight = v.getHeight();
            int childHeight = mSvLayout.getChildAt(0).getMeasuredHeight();
            int scrollY = v.getScrollY();
            if ((scrollY + layoutHeight) >= (childHeight - 80)) {
                mCurPage += 1;
                mHomePresenter.loadHomeData();
            }
        }
        return false;
    }

    private class GoodsListAdapter extends BaseListAdapter<Goods> {
        public GoodsListAdapter(Context context, List<Goods> data, int resId) {
            super(context, data, resId);
        }

        @Override
        public void convert(ViewHolder holder, final Goods goods) {
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("goods_id", goods.getGoods_id());
                    mContext.startActivity(intent);
                }
            });

            holder.displayImage(R.id.item_goods_image, goods.getImage().getBmiddle_pic())
                    .setText(R.id.item_goods_name, goods.getName())
                    .setText(R.id.item_goods_price, "￥" + goods.getGoods_price());
        }
    }

}
