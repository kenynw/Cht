package com.damenghai.chahuitong.adapter.viewholder;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.flea.FleaAddActivity;
import com.damenghai.chahuitong.module.flea.FleaDetailActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

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

    public FleaMyViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_flea_my);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Flea data) {
        mDvImage.setImageURI(Uri.parse(data.getGoods_image()));
        mTvTitle.setText(data.getGoods_name());
        mTvTime.setText(String.format(getContext().getString(R.string.text_publish_time), data.getGoods_add_time()));
        String heat = String.format(getContext().getString(R.string.text_flea_heat), data.getGoods_click(), data.getCommentnum(), data.getFlea_collect_num());
        mTvHeat.setText(heat);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FleaDetailActivity.class);
            intent.putExtra("flea_id", data.getGoods_id());
            getContext().startActivity(intent);
        });
        mBtnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FleaAddActivity.class);
            intent.putExtra("flea", data);
            getContext().startActivity(intent);
        });
        mBtnDel.setOnClickListener(v -> DialogFactory.createGenericDialog(getContext(), R.string.dialog_delete_flea, (dialog, which) -> {
            FleaModel.getInstance().delFlea(data.getGoods_id()).subscribe(new ServiceResponse<Boolean>() {
                @Override
                public void onNext(Boolean result) {
                    LUtils.toastLong(R.string.toast_del_success);
                }
            });
        }).show());
    }
}
