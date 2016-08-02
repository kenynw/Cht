package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.PersonalAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataFragment;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.module.settings.SettingsActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainPersonalPresenter.class)
public class MainPersonalFragment extends BaseDataFragment<MainPersonalPresenter, User> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_title)
    TextView mTvTitle;

    @Bind(R.id.layout_user_head)
    LinearLayout mLayoutHead;

    @Bind(R.id.dv_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.layout_user_info)
    LinearLayout mLayoutInfo;

    @Bind(R.id.tv_user_username)
    TextView mTvUsername;

    @Bind(R.id.tv_user_point)
    TextView mTvPoint;

    @Bind(R.id.tv_user_login)
    TextView mTvLogin;

    @Bind(R.id.btn_user_order_list)
    Button mBtnOrder;

    @Bind(R.id.btn_user_unpaid)
    Button mBtnUnpaid;

    @Bind(R.id.btn_user_paid)
    Button mBtnPaid;

    @Bind(R.id.btn_user_receive)
    Button mBtnReceive;

    @Bind(R.id.btn_user_uncomment)
    Button mBtnUncomment;

    @Bind(R.id.grid_item_personal)
    RecyclerView mGridItem;

    @Bind(R.id.tv_unpaid_tips)
    TextView mTvUnpaid;

    @Bind(R.id.tv_paid_tips)
    TextView mTvPaid;

    @Bind(R.id.tv_receive_tips)
    TextView mTvReceive;

    @Bind(R.id.tv_uncomment_tips)
    TextView mTvUncomment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_personal, container, false);
        ButterKnife.bind(this, view);

        initToolbar();

        mGridItem.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mGridItem.setAdapter(new PersonalAdapter(getActivity()));

        mLayoutHead.setOnClickListener(v -> getPresenter().isLogin());
        mBtnUnpaid.setOnClickListener(v -> getPresenter().showOrder(0));
        mBtnPaid.setOnClickListener(v -> getPresenter().showOrder(1));
        mBtnReceive.setOnClickListener(v -> getPresenter().showOrder(2));
        mBtnUncomment.setOnClickListener(v -> getPresenter().showOrder(3));
        mBtnOrder.setOnClickListener(v -> getPresenter().showOrder(4));

        return view;
    }

    @Override
    public void setData(User user) {
        mLayoutHead.setOnClickListener(v -> startActivity(new Intent(getActivity(), UserInfoActivity.class)));
        mDvAvatar.setImageURI(Uri.parse(user.getMember_avatar()));
        mTvUsername.append(user.getMember_name());
        mTvPoint.append(user.getMember_points());
        mLayoutInfo.setVisibility(View.VISIBLE);
        mTvLogin.setVisibility(View.GONE);
        if (user.getOrder_new_count() > 0) {
            mTvUnpaid.setVisibility(View.VISIBLE);
            mTvUnpaid.setText(String.valueOf(user.getOrder_new_count()));
        } else {
            mTvUnpaid.setVisibility(View.GONE);
        }

        if (user.getOrder_pay_count() > 0) {
            mTvPaid.setVisibility(View.VISIBLE);
            mTvPaid.setText(String.valueOf(user.getOrder_pay_count()));
        } else {
            mTvPaid.setVisibility(View.GONE);
        }

        if (user.getOrder_send_count() > 0) {
            mTvReceive.setVisibility(View.VISIBLE);
            mTvReceive.setText(String.valueOf(user.getOrder_send_count()));
        } else {
            mTvReceive.setVisibility(View.GONE);
        }

        if (user.getOrder_eval_count() > 0) {
            mTvUncomment.setVisibility(View.VISIBLE);
            mTvUncomment.setText(String.valueOf(user.getOrder_eval_count()));
        } else {
            mTvUncomment.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_personal, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initToolbar() {
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        if (activity.getSupportActionBar() != null)activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTvTitle.setText(R.string.title_activity_personal);
        mToolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
        super.setMenuVisibility(menuVisible);
    }
}
