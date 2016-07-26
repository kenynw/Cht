package com.damenghai.chahuitong.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.model.bean.People;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(UserInfoPresenter.class)
public class UserInfoActivity extends BaseDataActivity<UserInfoPresenter, People> {

    @Bind(R.id.rv_user_traces)
    EasyRecyclerView mRvTraces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_detail);
        ButterKnife.bind(this);

        mRvTraces.setLayoutManager(new LinearLayoutManager(this));
        mRvTraces.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRvTraces.setAdapter(getPresenter().getAdapter());
    }

    @Override
    public void setData(People people) {
        setToolbarTitle(people.getMember_name());
        getPresenter().getAdapter().addAll(people.getTrace_list());
        mRvTraces.scrollToPosition(0);
        getPresenter().getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(UserInfoActivity.this).inflate(R.layout.header_user_traces, parent, false);

                return view;
            }

            @Override
            public void onBindView(View view) {
                SimpleDraweeView avatar = (SimpleDraweeView) view.findViewById(R.id.dv_user_avatar);
                TextView username = (TextView) view.findViewById(R.id.tv_user_username);
                TextView tvTraces = (TextView) view.findViewById(R.id.tv_user_traces);
                TextView tvFollowing = (TextView) view.findViewById(R.id.tv_user_following);
                TextView tvFollowers = (TextView) view.findViewById(R.id.tv_user_followers);
                TextView tvArea = (TextView) view.findViewById(R.id.tv_user_area);
                TextView tvIntro = (TextView) view.findViewById(R.id.tv_user_introduction);

                avatar.setImageURI(Uri.parse(people.getMember_avatar()));
                username.setText(people.getMember_name());
                tvTraces.setText(String.valueOf(people.getTrace_count()));
                tvFollowing.setText(String.valueOf(people.getFollowing()));
                tvFollowers.setText(String.valueOf(people.getFollowers()));
                tvArea.setText(people.getMember_areainfo());
                if (!people.getMember_intro().isEmpty()) {
                    tvIntro.setVisibility(View.VISIBLE);
                    tvIntro.setText(people.getMember_intro());
                }
            }
        });
    }
}
