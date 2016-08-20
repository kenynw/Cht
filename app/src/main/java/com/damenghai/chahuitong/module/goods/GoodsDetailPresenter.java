package com.damenghai.chahuitong.module.goods;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FavoritesModel;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.mall.BuyPresenter;
import com.damenghai.chahuitong.module.mall.CartListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsDetailPresenter extends BaseDataActivityPresenter<GoodsDetailActivity, GoodsInfo>
        implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private int mGoodsId;

    private List<Fragment> mFragments;

    @Override
    protected void onCreateView(GoodsDetailActivity view) {
        super.onCreateView(view);
        mGoodsId = getView().getIntent().getIntExtra("goods_id", 0);
        initFragments();
        GoodsModel.getInstance().getGoodsDetail(mGoodsId).subscribe(getDataSubscriber());
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(GoodsBodyFragment.newInstance(mGoodsId + ""));
        mFragments.add(GoodsCommentFragment.newInstance(mGoodsId + ""));
        FragmentTransaction ft = getView().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_goods_detail, mFragments.get(0));
        ft.commit();
    }

    private boolean isLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            getView().startActivity(new Intent(getView(), LoginActivity.class));
            return false;
        }
        return true;
    }

    public void addFavorites() {

    }

    public void addCart() {
        if (isLogin()) {
            GoodsModel.getInstance().addCart(mGoodsId).subscribe(new ServiceResponse<String>() {
                @Override
                public void onNext(String s) {
                    super.onNext(s);
                    DialogFactory.createCartDialog(getView());
                }
            });
        }
    }

    public void toCart() {
        if (isLogin()) {
            getView().startActivity(new Intent(getView(), CartListActivity.class));
        }
    }

    public void toBuy() {
        if (isLogin()) {
            getView().startActivity(BuyPresenter.getStartIntent(getView(), mGoodsId + "|1", 0));
        }
    }

    public void share(Goods goods) {
        final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]{
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SINA
        };

        new ShareAction(getView())
                .setDisplayList(displayList)
                .withText(goods.getGoods_name())
                .withTitle("分享一款好茶")
                .withMedia(new UMImage(getView(), goods.getGoods_image_url()))
                .withTargetUrl(goods.getGoods_url())
                .open();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getView().getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.tab_goods_body:
                transaction.replace(R.id.container_goods_detail, mFragments.get(0));
                break;
            case R.id.tab_goods_comment:
                transaction.replace(R.id.container_goods_detail, mFragments.get(1));
                break;
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (isLogin()) {
            if (b) {
                FavoritesModel.getInstance().addFavorites(mGoodsId)
                        .compose(new DefaultTransform<>())
                        .subscribe(new ServiceResponse<String>() {
                            @Override
                            public void onNext(String result) {
                                LUtils.toast("收藏成功");
                            }
                        });
            } else {
                FavoritesModel.getInstance().deleteFavorites(mGoodsId)
                        .compose(new DefaultTransform<>())
                        .subscribe(new ServiceResponse<String>() {
                            @Override
                            public void onNext(String result) {
                                LUtils.toast("删除成功");
                            }
                        });
            }
        }
    }

}
