package com.damenghai.chahuitong.module.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.User;

public class UserInfoActivity extends BaseDataActivity<UserInfoPresenter, User> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_detail);
    }

    @Override
    public void setData(User user) {

    }
}
