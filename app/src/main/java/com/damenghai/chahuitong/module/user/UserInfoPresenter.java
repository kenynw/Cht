package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.UserModel;
import com.damenghai.chahuitong.model.bean.People;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.personal.ProfileActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserInfoPresenter extends BaseListActivityPresenter<UserInfoActivity, Trace> {

    public int mUserId;

    public People mPeople;

    @Override
    protected void onCreate(UserInfoActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUserId = getView().getIntent().getIntExtra("user_id", 0);
        if (getView().getIntent().getData() != null) {
            Uri uri = getView().getIntent().getData();
            mUserId = Integer.valueOf(uri.getQueryParameter("mid"));
        }
    }

    @Override
    protected void onCreateView(UserInfoActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().userHome(mUserId)
                .map(people -> {
                    getAdapter().removeAllHeader();
                    getAdapter().addHeader(new Header(people));
                    mPeople = people;
                    getView().setToolbarTitle(people.getMember_name());
                    getView().supportInvalidateOptionsMenu();
                    return people.getTrace_list();
                })
                .unsafeSubscribe(new ServiceResponse<List<Trace>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().stopRefresh();
                        getView().showError();
                    }

                    @Override
                    public void onNext(List<Trace> list) {
                        getAdapter().clear();
                        getAdapter().addAll(list);
                        setCurPage(2);
                        getView().getListView().scrollToPosition(0);
                    }
                });
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getUserTraceList(mUserId, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

    public void inform(String content) {
        TraceModel.getInstance().informHome(mUserId, content).subscribe(new ServiceResponse<Boolean>());
    }

    public People getPeople() {
        return mPeople;
    }

    public void showUserList(int type) {
        Intent intent = new Intent(getView(), FollowUserListActivity.class);
        intent.putExtra("user_id", mUserId);
        intent.putExtra("type", type);
        getView().startActivity(intent);
    }

    public class Header implements RecyclerArrayAdapter.ItemView {

        @Bind(R.id.dv_user_avatar)
        SimpleDraweeView mDvAvatar;

        @Bind(R.id.tv_user_username)
        TextView mTvUsername;

        @Bind(R.id.tv_user_traces)
        TextView mTvTraces;

        @Bind(R.id.ly_user_following)
        LinearLayout mLlFollowing;

        @Bind(R.id.tv_user_following)
        TextView mTvFollowing;

        @Bind(R.id.ly_user_followers)
        LinearLayout mLlFollowers;

        @Bind(R.id.tv_user_followers)
        TextView mTvFollowers;

        @Bind(R.id.tv_user_area)
        TextView mTvArea;

        @Bind(R.id.tv_user_introduction)
        TextView mTvIntro;

        @Bind(R.id.btn_user_follow)
        Button mBtnFollow;

        private People mPeople;

        private ServiceResponse<Integer> mRefreshSubscriber = new ServiceResponse<Integer>() {
            @Override
            public void onNext(Integer i) {
                onRefresh();
            }
        };

        public Header(People data) {
            this.mPeople = data;
        }

        @Override
        public View onCreateView(ViewGroup parent) {
            return LayoutInflater.from(getView()).inflate(R.layout.header_user_traces, parent, false);
        }

        @Override
        public void onBindView(View view) {
            ButterKnife.bind(this, view);
            mDvAvatar.setImageURI(Uri.parse(mPeople.getMember_avatar()));
            mLlFollowing.setOnClickListener(v -> showUserList(0));
            mLlFollowers.setOnClickListener(v -> showUserList(1));
            mTvUsername.setText(mPeople.getMember_name());
            mTvTraces.setText(String.valueOf(mPeople.getTrace_count()));
            mTvFollowing.setText(String.valueOf(mPeople.getFollowing()));
            mTvFollowers.setText(String.valueOf(mPeople.getFollowers()));
            mTvArea.setText(mPeople.getMember_areainfo());
            if (!mPeople.getMember_intro().isEmpty()) {
                mTvIntro.setVisibility(View.VISIBLE);
                mTvIntro.setText(mPeople.getMember_intro());
            }
            setRelation(mPeople.getRelation());
        }

        private void setRelation(int relation) {
            switch (relation) {
                case 2 :
                    mBtnFollow.setText(R.string.btn_relation_friend);
                    mBtnFollow.setOnClickListener(v -> FriendModel.getInstance().delFollow(mUserId).subscribe(mRefreshSubscriber));
                    break;
                case 3 :
                    mBtnFollow.setText(R.string.btn_edit);
                    mBtnFollow.setOnClickListener(v -> {
                        Intent i = new Intent(getView(), ProfileActivity.class);
                        getView().startActivity(i);
                    });
                    break;
                case 4 :
                    mBtnFollow.setText(R.string.btn_followed);
                    mBtnFollow.setOnClickListener(v -> FriendModel.getInstance().delFollow(mUserId).subscribe(mRefreshSubscriber));
                    break;
                default :
                    mBtnFollow.setText(R.string.btn_add_follow);
                    mBtnFollow.setOnClickListener(v -> FriendModel.getInstance().addFollow(mUserId).subscribe(mRefreshSubscriber));
                    break;
            }
        }

    }
}
