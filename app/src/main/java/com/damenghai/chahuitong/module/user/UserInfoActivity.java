package com.damenghai.chahuitong.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.module.trace.TraceListFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(UserInfoPresenter.class)
public class UserInfoActivity extends BaseDataActivity<UserInfoPresenter, User> {

    @Bind(R.id.dv_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_user_traces)
    TextView mTvTraces;

    @Bind(R.id.tv_user_following)
    TextView mTvFollowing;

    @Bind(R.id.tv_user_followers)
    TextView mTvFollowers;

    @Bind(R.id.tv_user_username)
    TextView mTvUsername;

    @Bind(R.id.tv_user_area)
    TextView mTvArea;

    @Bind(R.id.tv_user_introduction)
    TextView mTvIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(User user) {
        setToolbarTitle(user.getMember_name());
        mDvAvatar.setImageURI(Uri.parse(user.getMember_avatar()));
        mTvUsername.setText(user.getMember_name());
        mTvTraces.setText(String.valueOf(user.getTraces()));
        mTvArea.setText(user.getMember_areainfo());
//        TraceListFragment fragment = new TraceListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("mid", 0);
//        fragment.setArguments(bundle);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.container_user_trace, fragment);
//        ft.commit();
    }
}
