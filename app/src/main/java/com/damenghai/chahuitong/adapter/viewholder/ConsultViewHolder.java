package com.damenghai.chahuitong.adapter.viewholder;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Consult;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ConsultViewHolder extends BaseViewHolder<Consult> {

    @Bind(R.id.dv_consult_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_consult_username)
    TextView mTvUsername;

    @Bind(R.id.tv_consult_time)
    TextView mTvTime;

    @Bind(R.id.tv_consult_content)
    TextView mTvContent;

    public ConsultViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Consult data) {
        mDvAvatar.setImageURI(Uri.parse(data.getMember_avatar()));
        mTvUsername.setText(data.getMember_name());
        mTvTime.setText(data.getConsult_addtime());
        mTvContent.setText(data.getConsult_content());
    }
}
