package com.damenghai.chahuitong.module.flea;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.FleaConsultAdapter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaDetailPresenter extends BaseDataActivityPresenter<FleaDetailActivity, Flea>
        implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    private int mFleaID;

    private FleaConsultAdapter mConsultAdapter;

    private int mConsultPage;

    @Override
    protected void onCreate(FleaDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mFleaID = getView().getIntent().getIntExtra("flea_id", 0);
    }

    @Override
    protected void onCreateView(FleaDetailActivity view) {
        super.onCreateView(view);
        loadData();
        mConsultPage = 2;
    }

    private void loadData() {
        FleaModel.getInstance().getFleaDetail(mFleaID).unsafeSubscribe(getDataSubscriber());
    }

    public void loadMoreConsult() {
        FleaModel.getInstance().getConsultList(mConsultPage, mFleaID).subscribe(new ServiceResponse<BeanList<Consult>>(){
            @Override
            public void onNext(BeanList<Consult> consultBeanList) {
                super.onNext(consultBeanList);
                mConsultPage++;
                mConsultAdapter.addAll(consultBeanList.getList());
            }
        });
    }

    public FleaConsultAdapter getAdapter() {
        if (mConsultAdapter == null) mConsultAdapter = new FleaConsultAdapter(getView());
        return mConsultAdapter;
    }

    public void publishConsult(String content) {
        FleaModel.getInstance().saveConsult(content, mFleaID).subscribe(new ServiceResponse<Consult>() {
            @Override
            public void onNext(Consult consult) {
                LUtils.toast(R.string.toast_operate_success);
                LUtils.closeKeyboard(getView().getEtConsult());
                getView().getEtConsult().setText("");
                mConsultAdapter.insert(consult, 0);
                mConsultAdapter.notifyDataSetChanged();
            }
        });
    }

    public void showUser(int member_id) {
        Intent intent = new Intent(getView(), UserInfoActivity.class);
        intent.putExtra("user_id", member_id);
        getView().startActivity(intent);
    }

    public void share() {
        final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]{
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ,
                SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SINA
        };

        new ShareAction(getView())
                .setDisplayList(displayList)
                .withTitle(getDataSubject().getValue().getGoods_name())
                .withTargetUrl(getDataSubject().getValue().getGoods_url())
                .withText(getDataSubject().getValue().getGoods_abstract())
                .withMedia(new UMImage(getView(), getDataSubject().getValue().getGoods_image()))
                .open();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            getView().getBtnPublish().setEnabled(true);
        } else {
            getView().getBtnPublish().setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            FleaModel.getInstance().addFavorite(mFleaID).subscribe(new ServiceResponse<Boolean>() {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    compoundButton.setChecked(false);
                }

                @Override
                public void onNext(Boolean aBoolean) {
                    LUtils.toast("收藏成功");
                }
            });

        } else {
            FleaModel.getInstance().delFavorite(mFleaID).subscribe(new ServiceResponse<Boolean>() {

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    compoundButton.setChecked(true);
                }

                @Override
                public void onNext(Boolean aBoolean) {
                    LUtils.toast("删除成功");
                }
            });
        }
    }
}
