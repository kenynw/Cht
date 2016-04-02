package com.damenghai.chahuitong.module.main;

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
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BannerPagerAdapter;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.GalleyAdapter;
import com.damenghai.chahuitong.adapter.GoodsRecycleGridAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.module.mall.GoodsDetailActivity;
import com.damenghai.chahuitong.module.special.BargainActivity;
import com.damenghai.chahuitong.module.special.SampleActivity;
import com.damenghai.chahuitong.module.special.ValuerActivity;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.damenghai.chahuitong.widget.WrapHeightGridManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 *
 */
@RequiresPresenter(HomePresenter.class)
public class HomeFragment extends BaseDataFragment<HomePresenter, Home> implements OnTouchListener {

    @Bind(R.id.sv_home_layout)
    ScrollView mSvLayout;

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

    @Bind(R.id.ll_home_Bargain)
    LinearLayout mLayoutBargain;

    @Bind(R.id.rcv_home_galley)
    RecyclerView mRcvGalley;

    @Bind(R.id.gv_home_guess)
    RecyclerView mRgvGuess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_home, container, false);
        ButterKnife.bind(this, view);

        mSvLayout.setOnTouchListener(this);
        return view;
    }

    @Override
    public void setData(Home home) {
        super.setData(home);

        mViewPager.setAdapter(new BannerPagerAdapter(getActivity(), home.getAdds()));
        mIndicator.setViewPager(mViewPager);

        mDvSample.setImageURI(Uri.parse(home.getSample_image()));
        mDvBargain.setImageURI(Uri.parse(home.getXianshi().getImage()));

        mRcvGalley.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRcvGalley.setAdapter(new GalleyAdapter(getActivity(), home.getTasters_list()));

        mRgvGuess.setLayoutManager(new WrapHeightGridManager(getActivity(), 2));
        mRgvGuess.setAdapter(new GoodsRecycleGridAdapter(getActivity(), home.getGuess_list()));

        mLayoutSample.setOnClickListener(v -> startActivity(new Intent(getActivity(), SampleActivity.class)));
        mLayoutBargain.setOnClickListener(v -> startActivity(new Intent(getActivity(), BargainActivity.class)));
    }

    public void nextPage(List<Goods> list) {
        ((GoodsRecycleGridAdapter) mRgvGuess.getAdapter()).addList(list);
    }

    @OnClick({R.id.iv_home_recommend, R.id.ll_valuator_layout})
    void valuerList() {
        startActivity(new Intent(getActivity(), ValuerActivity.class));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int layoutHeight = v.getHeight();
            int childHeight = mSvLayout.getChildAt(0).getMeasuredHeight();
            int scrollY = v.getScrollY();
            if ((scrollY + layoutHeight) >= (childHeight - 80)) {
                getPresenter().nextPage();
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
            holder.getConvertView().setOnClickListener(v -> {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", goods.getGoods_id());
                mContext.startActivity(intent);
            });

            holder.displayImage(R.id.goods_dv_thumb, goods.getGoods_image_url())
                    .setText(R.id.goods_tv_title, goods.getGoods_name())
                    .setText(R.id.goods_tv_price, "￥" + goods.getGoods_price());
        }
    }

}
