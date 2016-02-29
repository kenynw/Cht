package com.damenghai.chahuitong.view.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class EmptyFragment extends BaseFragment {
    public static final String KEY_MESSAGE = "EmptyFragment:Message";

    @Bind(R.id.tv_comment_empty)
    TextView mTvEmpty;

    private String mMessage;

    public EmptyFragment() {}

    public static EmptyFragment newInstance(String message) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMessage = getArguments().getString(KEY_MESSAGE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        ButterKnife.bind(this, view);
        if (!TextUtils.isEmpty(mMessage)) mTvEmpty.setText(mMessage);
        return view;
    }
}
