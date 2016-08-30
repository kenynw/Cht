package com.damenghai.chahuitong.module.main;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.UserModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.order.OrderActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainPersonalPresenter extends BaseDataFragmentPresenter<MainPersonalFragment, User> {

    private final int REQUEST_CODE_LOGIN = 0;

    @Override
    protected void onCreateView(MainPersonalFragment view) {
        super.onCreateView(view);
        loadData();
    }

    public void loadData() {
        UserModel.getInstance().getUserInfo().subscribe(getSubscriber());
    }

    public void showOrder(int position) {
        if (isLogin()) {
            Intent intent = new Intent(getView().getActivity(), OrderActivity.class);
            intent.putExtra("position", position);
            getView().startActivity(intent);
        }
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            getView().startActivityForResult(new Intent(getView().getActivity(), LoginActivity.class), REQUEST_CODE_LOGIN);
            return false;
        }
        return true;
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOGIN) {
                UserModel.getInstance().getUserInfo().unsafeSubscribe(new ServiceResponse<User>() {
                    @Override
                    public void onNext(User user) {
                        getView().setData(user);
                    }
                });
            }
        }
        super.onResult(requestCode, resultCode, data);
    }
}
