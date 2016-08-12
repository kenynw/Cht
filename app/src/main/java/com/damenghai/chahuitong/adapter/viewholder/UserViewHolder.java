package com.damenghai.chahuitong.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserViewHolder<M extends User> extends BaseViewHolder<M> {

    @Bind(R.id.dv_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_user_username)
    TextView mTvUsername;

    @Bind(R.id.btn_user_follow)
    Button mTvFollow;

    public UserViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_list_user);
    }

    public UserViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(User data) {
        mDvAvatar.setImageURI(Uri.parse(data.getMember_avatar()));
        mTvUsername.setText(data.getMember_name());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), UserInfoActivity.class);
            i.putExtra("user_id", data.getMember_id());
            getContext().startActivity(i);
        });
        changeState(data.getFollow_state(), data);
    }

    private void changeState(int state, User user) {
        switch (state) {
            case 0:
                mTvFollow.setText(R.string.btn_add_follow);
                mTvFollow.setOnClickListener(v -> {
                    FriendModel.getInstance().addFollow(user.getMember_id())
                            .subscribe(new ServiceResponse<Integer>() {
                                @Override
                                public void onNext(Integer integer) {
                                    changeState(integer, user);
                                }
                            });
                });
                break;
            case 1:
                mTvFollow.setText(R.string.btn_followed);
                mTvFollow.setOnClickListener(v -> FriendModel.getInstance().delFollow(user.getMember_id())
                        .subscribe(new ServiceResponse<Integer>() {
                            @Override
                            public void onNext(Integer integer) {
                                changeState(integer, user);
                            }
                        }));
                break;
            case 2:
                mTvFollow.setText(R.string.btn_relation_friend);
                mTvFollow.setOnClickListener(v -> FriendModel.getInstance().delFollow(user.getMember_id())
                        .subscribe(new ServiceResponse<Integer>() {
                            @Override
                            public void onNext(Integer integer) {
                                changeState(integer, user);
                            }
                        }));
                break;
        }
    }

}
