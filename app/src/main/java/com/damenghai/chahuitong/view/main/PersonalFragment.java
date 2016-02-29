package com.damenghai.chahuitong.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseFragment;
import com.damenghai.chahuitong.view.user.LoginActivity;
import com.damenghai.chahuitong.view.user.RegisterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PersonalFragment extends BaseFragment {

    @Bind(R.id.btn_personal_login)
    Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_personal, null);

        ButterKnife.bind(this, view);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(LoginActivity.class);
            }
        });

        return view;
    }

    @OnClick(R.id.btn_personal_register)
    void toRegister() {
        openActivity(RegisterActivity.class);
    }

}
