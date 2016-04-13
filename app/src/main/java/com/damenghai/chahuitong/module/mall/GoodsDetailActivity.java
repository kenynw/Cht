package com.damenghai.chahuitong.module.mall;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ImagePagerAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.model.bean.Attribute;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.damenghai.chahuitong.widget.WrapHeightGridView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

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

    @Bind(R.id.tv_goods_origin)
    TextView mTvOrigin;

    @Bind(R.id.grid_detail_attrs)
    WrapHeightGridView mGridAttrs;

    @Bind(R.id.radio_goods_detail)
    RadioGroup mTabDetail;

    @Bind(R.id.tab_goods_comment)
    RadioButton mBtnComment;

    @Bind(R.id.btn_goods_detail_share)
    Button mBtnShare;

    @Bind(R.id.btn_goods_detail_favorites)
    Button mBtnFavorites;

    @Bind(R.id.btn_goods_detail_service)
    Button mBtnService;

    @Bind(R.id.btn_goods_detail_add_cart)
    Button mBtnAddCart;

    @Bind(R.id.btn_goods_detail_buy)
    Button mBtnBuy;

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity_detail);
        setToolbarTitle(R.string.title_activity_goods);
        ButterKnife.bind(this);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getPresenter().toCart();
                return true;
            }
        });
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
        final List<Image> imageList = new ArrayList<>();
        for (String imageStr : images) {
            Image image = new Image(null, imageStr, null);
            imageList.add(image);
        }
        mPagerImage.setAdapter(new ImagePagerAdapter(this, imageList));
        mIndicator.setViewPager(mPagerImage);

        mGridAttrs.setAdapter(new AttrsGridAdapter(this, goods.getGoods_attr(), R.layout.item_grid_attrs));

        mFragments = new ArrayList<>();
        mFragments.add(GoodsBodyFragment.newInstance(goods.getGoods_id()));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_goods_detail, mFragments.get(0));
        ft.commit();

        mTabDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_goods_body:
                        FragmentTransaction bodyTransaction = getSupportFragmentManager().beginTransaction();
                        bodyTransaction.replace(R.id.container_goods_detail, mFragments.get(0));
                        bodyTransaction.commit();
                        break;
                    case R.id.tab_goods_comment:
                        FragmentTransaction commentTransaction = getSupportFragmentManager().beginTransaction();
                        if (mFragments.size() < 2) {
                            if (goodsInfo.getGoods_evaluate_info().size() <= 0) {
                                mFragments.add(new EmptyFragment());
                            } else {
                                mFragments.add(GoodsCommentFragment.newInstance(goodsInfo.getGoods_evaluate_info()));
                            }
                        }
                        commentTransaction.replace(R.id.container_goods_detail, mFragments.get(1));
                        commentTransaction.commit();
                        break;
                }
            }
        });

        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                SHARE_MEDIA.SINA
                        };
                new ShareAction(GoodsDetailActivity.this)
                        .setDisplayList(displayList)
                        .withText(goods.getGoods_name())
                        .withTitle(goods.getGoods_name())
                        .withMedia(new UMImage(GoodsDetailActivity.this, imageList.get(0).getBmiddle_pic()))
                        .withTargetUrl(Config.BASE_GOODS_DETAIL_URL + goods.getGoods_id())
                        .open();
            }
        });

        mBtnFavorites.setOnClickListener(v -> getPresenter().addFavorites());
        mBtnService.setOnClickListener(v -> LUtils.toast("客服系统正在完善中..."));
        mBtnAddCart.setOnClickListener(v -> getPresenter().addCart());
        mBtnBuy.setOnClickListener(v -> getPresenter().toBuy());
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
