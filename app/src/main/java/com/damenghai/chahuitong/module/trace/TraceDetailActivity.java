package com.damenghai.chahuitong.module.trace;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TraceCommentAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Trace;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(TraceDetailPresenter.class)
public class TraceDetailActivity extends BaseDataActivity<TraceDetailPresenter, Trace> {

    @Bind(R.id.dv_trace_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_trace_username)
    TextView mTvUsername;

    @Bind(R.id.btn_trace_follow)
    Button mBtnFollow;

    @Bind(R.id.tv_trace_time)
    TextView mTvTime;

    @Bind(R.id.tv_trace_content)
    TextView mTvContent;

    @Bind(R.id.dv_trace_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.rcv_trace_comment)
    EasyRecyclerView mRcvComment;

    @Bind(R.id.et_trace_comment)
    EditText mEtComment;

    @Bind(R.id.btn_trace_add_comment)
    Button mBtnAddComment;

    @Bind(R.id.btn_trace_comment)
    Button mBtnComment;

    @Bind(R.id.btn_trace_like)
    Button mBtnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trace_activity_detail);
        setToolbarTitle(R.string.title_trace_detail);
        ButterKnife.bind(this);

        mRcvComment.setLayoutManager(new LinearLayoutManager(this));
        mRcvComment.setEmptyView(R.layout.defalut_view_list_empty);
        mEtComment.addTextChangedListener(getPresenter());
        mBtnAddComment.setOnClickListener(v -> getPresenter().addComment(mEtComment.getText().toString()));
    }

    @Override
    public void setData(Trace trace) {
        mDvAvatar.setImageURI(Uri.parse(trace.getTrace_memberavatar()));
        mDvAvatar.setOnClickListener(v -> getPresenter().showUserInfo(trace.getTrace_memberid()));
        mTvUsername.setText(trace.getTrace_membername());
        mTvUsername.setOnClickListener(v -> getPresenter().showUserInfo(trace.getTrace_memberid()));
        mTvContent.setText(trace.getTrace_title());
        mDvImage.setImageURI(Uri.parse(trace.getTrace_image()));
        mBtnComment.setText(String.format(getString(R.string.btn_trace_comment), trace.getTrace_commentcount() > 0 ? trace.getTrace_commentcount() : ""));
        mBtnLike.setText(String.format(getString(R.string.btn_trace_like), trace.getTrace_likecount() > 0 ? trace.getTrace_likecount() : ""));
        mBtnLike.setOnClickListener(v -> getPresenter().addLike());
        mRcvComment.setAdapter(new TraceCommentAdapter(this, trace.getComment_list()));
        mTvTime.setText(trace.getTrace_addtime());
        setRelation(trace);
    }

    public Button getSubmitBtn() {
        return mBtnAddComment;
    }

    public void setRelation(Trace trace) {
        switch (trace.getRelation()) {
            case 1:
                mTvTime.setVisibility(View.GONE);
                mBtnFollow.setVisibility(View.VISIBLE);
                mBtnFollow.setText(R.string.btn_add_follow);
                mBtnFollow.setOnClickListener(v -> getPresenter().addFollow());
                break;
            case 2 :
                mTvTime.setVisibility(View.GONE);
                mBtnFollow.setVisibility(View.VISIBLE);
                mBtnFollow.setText(R.string.btn_relation_friend);
                mBtnFollow.setOnClickListener(v -> getPresenter().delFollow());
                break;
            case 4:
                mTvTime.setVisibility(View.GONE);
                mBtnFollow.setVisibility(View.VISIBLE);
                mBtnFollow.setText(R.string.btn_followed);
                mBtnFollow.setOnClickListener(v -> getPresenter().delFollow());
                break;
            default :
                mTvTime.setVisibility(View.VISIBLE);
                mBtnFollow.setVisibility(View.GONE);
                break;
        }
    }

}
