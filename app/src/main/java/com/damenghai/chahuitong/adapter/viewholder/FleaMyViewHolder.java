package com.damenghai.chahuitong.adapter.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.flea.FleaAddActivity;
import com.damenghai.chahuitong.module.flea.FleaDetailActivity;
import com.damenghai.chahuitong.module.flea.MyFleaPresenter;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaMyViewHolder extends BaseViewHolder<Flea> {

    @Bind(R.id.dv_flea_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.tv_flea_title)
    TextView mTvTitle;

    @Bind(R.id.tv_flea_heat)
    TextView mTvHeat;

    @Bind(R.id.tv_flea_time)
    TextView mTvTime;

    @Bind(R.id.btn_flea_my_share)
    Button mBtnShare;

    @Bind(R.id.btn_flea_my_edit)
    Button mBtnEdit;

    @Bind(R.id.btn_flea_my_del)
    Button mBtnDel;

    private MyFleaPresenter mPresenter;

    public FleaMyViewHolder(ViewGroup parent, MyFleaPresenter presenter) {
        super(parent, R.layout.item_list_flea_my);
        mPresenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Flea flea) {
        mDvImage.setImageURI(Uri.parse(flea.getGoods_image()));
        mTvTitle.setText(flea.getGoods_name());
        mTvTime.setText(String.format(getContext().getString(R.string.text_publish_time), flea.getGoods_add_time()));
        String heat = String.format(getContext().getString(R.string.text_flea_heat), flea.getGoods_click(), flea.getCommentnum(), flea.getFlea_collect_num());
        mTvHeat.setText(heat);
        mBtnDel.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setMessage(R.string.dialog_title_delete_trace)
                    .setTitle(R.string.dialog_delete_trace)
                    .setNegativeButton(R.string.btn_cancel, null)
                    .setPositiveButton(R.string.btn_ok, ((dialogInterface, i) -> FleaModel.getInstance().delFlea(flea.getGoods_id())
                            .subscribe(new ServiceResponse<Boolean>() {
                                @Override
                                public void onNext(Boolean result) {
                                    mPresenter.onRefresh();
                                    LUtils.toastLong(R.string.toast_del_success);
                                }
                            })))
                    .show();

            DialogFactory.createGenericDialog(getContext(), R.string.dialog_delete_flea, (dialog, which) -> {
                FleaModel.getInstance().delFlea(flea.getGoods_id()).subscribe(new ServiceResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        mPresenter.onRefresh();
                        LUtils.toastLong(R.string.toast_del_success);
                    }
                });
            }).show();
        });
        mBtnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FleaAddActivity.class);
            intent.putExtra("flea", flea);
            getContext().startActivity(intent);
        });
        mBtnShare.setOnClickListener(v -> share(flea));
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FleaDetailActivity.class);
            intent.putExtra("flea_id", flea.getGoods_id());
            getContext().startActivity(intent);
        });
    }

    private void share(Flea flea) {
        final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]{
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ,
                SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SINA
        };

        new ShareAction((Activity) getContext())
                .setDisplayList(displayList)
                .withTitle(flea.getGoods_name() + "的茶友圈动态")
                .withText(flea.getGoods_abstract())
                .withMedia(new UMImage(getContext(), flea.getGoods_image()))
                .open();
    }

}
