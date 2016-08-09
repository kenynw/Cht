package com.damenghai.chahuitong.module.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.MessageCount;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MessagePresenter.class)
public class MessageActivity extends BaseDataActivity<MessagePresenter, MessageCount> {

    @Bind(R.id.btn_msg_fans)
    Button mBtnFans;

    @Bind(R.id.tv_badge_fans)
    TextView mTvFansNum;

    @Bind(R.id.btn_msg_comment)
    Button mBtnComment;

    @Bind(R.id.tv_badge_comment)
    TextView mTvCommentNum;

    @Bind(R.id.btn_msg_at)
    Button mBtnAt;

    @Bind(R.id.tv_badge_at)
    TextView mTvAtNum;

    @Bind(R.id.btn_msg_system)
    Button mBtnSystem;

    @Bind(R.id.tv_badge_system)
    TextView mTvSystemNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_message);
        setToolbarTitle(R.string.title_activity_message);
        ButterKnife.bind(this);

        mBtnFans.setOnClickListener(v -> getPresenter().showMsgList(MessageListPresenter.TYPE_FANS, mBtnFans.getText()));
        mBtnComment.setOnClickListener(v -> getPresenter().showMsgList(MessageListPresenter.TYPE_COMMENT, mBtnComment.getText()));
        mBtnAt.setOnClickListener(v -> getPresenter().showMsgList(MessageListPresenter.TYPE_AT, mBtnAt.getText()));
        mBtnSystem.setOnClickListener(v -> getPresenter().showMsgList(MessageListPresenter.TYPE_SYSTEM, mBtnSystem.getText()));
    }

    @Override
    public void setData(MessageCount count) {
        if (count.getCount_new_msg() > 0) {
            if (count.getNew_fans() > 0) {
                mTvFansNum.setText(String.valueOf(count.getNew_fans()));
                mTvFansNum.setVisibility(View.VISIBLE);
            } else {
                mTvFansNum.setVisibility(View.GONE);
            }
            if (count.getNew_comment() > 0) {
                mTvCommentNum.setText(String.valueOf(count.getNew_comment()));
                mTvCommentNum.setVisibility(View.VISIBLE);
            } else {
                mTvCommentNum.setVisibility(View.GONE);
            }
            if (count.getNew_at() > 0) {
                mTvAtNum.setText(String.valueOf(count.getNew_at()));
                mTvAtNum.setVisibility(View.VISIBLE);
            } else {
                mTvAtNum.setVisibility(View.GONE);
            }
            if (count.getNew_system() > 0) {
                mTvSystemNum.setText(String.valueOf(count.getNew_system()));
                mTvSystemNum.setVisibility(View.VISIBLE);
            } else {
                mTvSystemNum.setVisibility(View.GONE);
            }
        }
    }

}
