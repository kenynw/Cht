package com.damenghai.chahuitong.module.goods;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.ImagePagerAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.model.bean.Attribute;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GoodsDetailPresenter.class)
public class GoodsDetailActivity extends BaseDataActivity<GoodsDetailPresenter, GoodsInfo> {

    @Bind(R.id.layout_content)
    ScrollView mSvLayout;

    @Bind(R.id.pager_goods_image)
    HeadViewPager mPagerImage;

    @Bind(R.id.indicator_goods)
    CirclePageIndicator mIndicator;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.tv_goods_old_price)
    TextView mTvOldPrice;

    @Bind(R.id.tv_goods_freight)
    TextView mTvFreight;

    @Bind(R.id.tv_goods_sales)
    TextView mTvSales;

    @Bind(R.id.grid_detail_attrs)
    RecyclerView mGridAttrs;

    @Bind(R.id.radio_goods_detail)
    RadioGroup mTabDetail;

    @Bind(R.id.tab_goods_comment)
    RadioButton mBtnComment;

    @Bind(R.id.btn_goods_detail_share)
    Button mBtnShare;

    @Bind(R.id.btn_goods_detail_favorites)
    ToggleButton mBtnFav;

    @Bind(R.id.btn_goods_detail_service)
    Button mBtnService;

    @Bind(R.id.btn_goods_detail_add_cart)
    Button mBtnAddCart;

    @Bind(R.id.btn_goods_detail_buy)
    Button mBtnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity_detail);
        setToolbarTitle(R.string.title_activity_goods);
        ButterKnife.bind(this);
        getToolbar().setOnMenuItemClickListener(item -> {
            getPresenter().toCart();
            return true;
        });

        mGridAttrs.setLayoutManager(new GridLayoutManager(this, 3));
        mTabDetail.setOnCheckedChangeListener(getPresenter());
        mBtnService.setOnClickListener(v -> getPresenter().toCart());
        mBtnAddCart.setOnClickListener(v -> getPresenter().addCart());
        mBtnBuy.setOnClickListener(v -> getPresenter().toBuy());
    }

    @Override
    public void setData(final GoodsInfo goodsInfo) {
        final Goods goods = goodsInfo.getGoods_info();
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(String.format(getString(R.string.text_rmb), goods.getGoods_price()));
        if (goods.getGoods_price() < goods.getGoods_marketprice()) {
            mTvOldPrice.setVisibility(View.VISIBLE);
            mTvOldPrice.setText(String.format(getString(R.string.text_rmb), goods.getGoods_marketprice()));
            mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (goods.getGoods_freight() <= 0)
            mTvFreight.setText(String.format(getString(R.string.label_goods_freight), "包邮"));
        else mTvFreight.setText(String.format(getString(R.string.label_goods_freight), goods.getGoods_freight()));
        mTvSales.append(goods.getGoods_salenum());

        String[] images = goodsInfo.getGoods_image().split(",");
        if (images.length > 0) {
            goods.setGoods_image_url(images[0]);
            final ArrayList<Image> imageList = new ArrayList<>();
            for (String imageStr : images) {
                Image image = new Image();
                image.setThumb_mid(imageStr);
                imageList.add(image);
            }
            mPagerImage.setAdapter(new ImagePagerAdapter(this, imageList));
        }
        mIndicator.setViewPager(mPagerImage);
        mGridAttrs.setAdapter(new AttrsGridAdapter(this, goods.getGoods_attr()));

        mBtnShare.setOnClickListener(v -> getPresenter().share(goods));

        mBtnFav.setChecked(goods.is_favorite());
        mBtnFav.setOnCheckedChangeListener(getPresenter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_mall, menu);
        return true;
    }

    class AttrsGridAdapter extends RecyclerArrayAdapter<Attribute> {

        public AttrsGridAdapter(Context context, List<Attribute> objects) {
            super(context, objects);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new AttrsViewHolder(parent);
        }

        class AttrsViewHolder extends BaseViewHolder<Attribute> {

            @Bind(R.id.tv_attr_name)
            TextView mTvName;

            @Bind(R.id.tv_attr_value)
            TextView mTvValue;

            public AttrsViewHolder(ViewGroup parent) {
                super(parent, R.layout.item_grid_attrs);
                ButterKnife.bind(this, itemView);
            }

            @Override
            public void setData(Attribute data) {
                mTvName.setText(data.getAttr_name() + ": ");
                mTvValue.setText(data.getAttr_value());
            }
        }

    }

}
