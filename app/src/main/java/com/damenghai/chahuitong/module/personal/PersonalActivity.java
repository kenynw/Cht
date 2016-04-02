package com.damenghai.chahuitong.module.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.PersonalGridAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.module.address.AddressListActivity;
import com.damenghai.chahuitong.module.order.OrderListActivity;
import com.damenghai.chahuitong.module.settings.ProfileActivity;
import com.damenghai.chahuitong.module.settings.SettingsActivity;
import com.damenghai.chahuitong.widget.DividerGridItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(PersonalPresenter.class)
public class PersonalActivity extends BaseDataActivity<PersonalPresenter, User> {

    @Bind(R.id.dv_user_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.layout_user_info)
    LinearLayout mLayoutInfo;

    @Bind(R.id.tv_user_username)
    TextView mTvUsername;

    @Bind(R.id.tv_user_point)
    TextView mTvPoint;

    @Bind(R.id.layout_user_login)
    FrameLayout mLayoutLogin;

    @Bind(R.id.grid_item_personal)
    RecyclerView mGridItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_personal);
        setToolbarTitle(R.string.title_activity_personal);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setData(User user) {
        mDvAvatar.setImageURI(Uri.parse(user.getAvatar()));
        mTvUsername.append(user.getUser_name());
        mTvPoint.append(user.getPoint());
        mLayoutInfo.setVisibility(View.VISIBLE);
        mLayoutLogin.setVisibility(View.GONE);
    }

    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mGridItem.setLayoutManager(layoutManager);
        mGridItem.setAdapter(new PersonalGridAdapter(this));
        mGridItem.addItemDecoration(new DividerGridItemDecoration(this));
        getToolbar().setOnMenuItemClickListener(item -> {
            startActivity(new Intent(PersonalActivity.this, SettingsActivity.class));
            return true;
        });
    }

    public void toOrders(View view) {
        if (getPresenter().isLogin()) {
            Intent intent = new Intent(this, OrderListActivity.class);
            switch (view.getId()) {
                case R.id.btn_user_unpaid:
                    intent.putExtra("position", 0);
                    break;
                case R.id.btn_user_paid:
                    intent.putExtra("position", 1);
                    break;
                case R.id.btn_user_receive:
                    intent.putExtra("position", 2);
                    break;
                case R.id.btn_user_uncomment:
                    intent.putExtra("position", 3);
                    break;
                default:
                    intent.putExtra("position", 4);
                    break;
            }
            startActivity(intent);
        }
    }

    public void toLogin(View view) {
        getPresenter().isLogin();
    }

    public void toAddress(View view) {
        if (getPresenter().isLogin()){
            startActivity(new Intent(this, AddressListActivity.class));
        }
    }

    public void toProfile(View view) {
        if (getPresenter().isLogin()) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        return true;
    }

}
