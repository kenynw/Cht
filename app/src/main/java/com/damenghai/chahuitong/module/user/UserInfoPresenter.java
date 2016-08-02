package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TraceAdapter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.UserModel;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.People;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserInfoPresenter extends BaseDataActivityPresenter<UserInfoActivity, People> implements RecyclerArrayAdapter.OnLoadMoreListener {

    private int mUserId;

    private int mCurPage;

    private TraceAdapter mAdapter;

    @Override
    protected void onCreate(UserInfoActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUserId = getView().getIntent().getIntExtra("user_id", 0);
        if (getView().getIntent().getData() != null) {
            Uri uri = getView().getIntent().getData();
            LUtils.log(uri.getQueryParameter("mid"));
            mUserId = Integer.valueOf(uri.getQueryParameter("mid"));
        }
    }

    public void inform(String content) {
        TraceModel.getInstance().informHome(mUserId, content).subscribe(new ServiceResponse<Boolean>());
    }

    @Override
    protected void onCreateView(UserInfoActivity view) {
        super.onCreateView(view);
        mCurPage = 2;
        UserModel.getInstance().userHome(mUserId).subscribe(getDataSubscriber());
    }

    public void showUserList(int type) {
        Intent intent = new Intent(getView(), FollowUserListActivity.class);
        intent.putExtra("user_id", mUserId);
        intent.putExtra("type", type);
        getView().startActivity(intent);
    }

    public TraceAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new TraceAdapter(getView());
        }
        mAdapter.setMore(R.layout.default_footer_load_more, this);
        mAdapter.setError(R.layout.default_footer_load_error);
        mAdapter.setNoMore(R.layout.default_footer_no_more);
        return mAdapter;
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getTraceList(mUserId, mCurPage)
                .subscribe(new ServiceResponse<BeanList<Trace>>() {
                    @Override
                    public void onNext(BeanList<Trace> traceBeanList) {
                        mAdapter.addAll(traceBeanList.getList());
                        mCurPage++;
                    }
                });
    }
}
