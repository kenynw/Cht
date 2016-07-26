package com.damenghai.chahuitong.module.goods;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ImagePagerAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.model.bean.Attribute;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.damenghai.chahuitong.widget.WrapHeightGridView;

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
    WrapHeightGridView mGridAttrs;

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

        mTabDetail.setOnCheckedChangeListener(getPresenter());
        mBtnService.setOnClickListener(v -> LUtils.toast("客服系统正在完善中..."));
        mBtnAddCart.setOnClickListener(v -> getPresenter().addCart());
        mBtnBuy.setOnClickListener(v -> getPresenter().toBuy());
    }

    @Override
    public void setData(final GoodsInfo goodsInfo) {
        final Goods goods = goodsInfo.getGoods_info();
        mTvName.setText(goods.getGoods_name());
        mTvPrice.append(TextUtils.isEmpty(goods.getPromotion_price()) ? goods.getGoods_price() : goods.getPromotion_price());
        mTvOldPrice.append(goods.getGoods_marketprice());
        mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTvFreight.append(goods.getGoods_freight());
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
        mGridAttrs.setAdapter(new AttrsGridAdapter(this, goods.getGoods_attr(), R.layout.item_grid_attrs));

        mBtnShare.setOnClickListener(v -> getPresenter().share(goods));

        mBtnFav.setChecked(goods.is_favorite());
        mBtnFav.setOnCheckedChangeListener(getPresenter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class AttrsGridAdapter extends BaseListAdapter<Attribute> {

        public AttrsGridAdapter(Context context, List<Attribute> data, int resId) {
            super(context, data, resId);
        }

        @Override
        public void convert(ViewHolder holder, Attribute attr) {
            holder.setText(R.id.tv_attr_name, attr.getAttr_name() + "：")
                    .setText(R.id.tv_attr_value, attr.getAttr_value());
        }
    }

}
