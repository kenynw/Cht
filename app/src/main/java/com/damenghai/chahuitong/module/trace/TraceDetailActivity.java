package com.damenghai.chahuitong.module.trace;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.expansion.list.DividerItemDecoration;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.utils.LUtils;
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

    @Bind(R.id.tv_trace_img_count)
    TextView mTvImgNum;

    @Bind(R.id.rcv_trace_comment)
    EasyRecyclerView mRcvComment;

    @Bind(R.id.et_add)
    EditText mEtComment;

    @Bind(R.id.btn_add)
    Button mBtnAddComment;

    @Bind(R.id.btn_trace_share)
    Button mBtnShare;

    @Bind(R.id.btn_trace_comment)
    Button mBtnComment;

    @Bind(R.id.btn_trace_like)
    RadioButton mBtnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trace_activity_detail);
        setToolbarTitle(R.string.title_trace_detail);
        ButterKnife.bind(this);

        mRcvComment.setLayoutManager(new LinearLayoutManager(this));
        mRcvComment.setEmptyView(R.layout.empty_list_comment);
        mRcvComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mEtComment.addTextChangedListener(getPresenter());
        mBtnAddComment.setOnClickListener(v -> getPresenter().addComment(mEtComment.getText().toString().trim()));
    }

    @Override
    public void setData(Trace trace) {
        mDvAvatar.setImageURI(Uri.parse(trace.getTrace_memberavatar()));
        mDvAvatar.setOnClickListener(v -> getPresenter().showUserInfo(trace.getTrace_memberid()));
        mTvUsername.setText(trace.getTrace_membername());
        mTvUsername.setOnClickListener(v -> getPresenter().showUserInfo(trace.getTrace_memberid()));
        mTvContent.setText(Html.fromHtml(trace.getTrace_title()));
        mTvContent.setClickable(true);
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
        mDvImage.setImageURI(Uri.parse(trace.getTrace_image()));
        mDvImage.setOnClickListener(v -> getPresenter().showImageDetail(trace.getTrace_image_list()));
        mTvImgNum.setText(String.valueOf(trace.getTrace_image_list().size()));
        mBtnComment.setText(String.format("%s", trace.getTrace_commentcount() > 0 ? trace.getTrace_commentcount() : getString(R.string.btn_trace_comment)));
        mBtnShare.setOnClickListener(v -> getPresenter().share());
        mBtnLike.setText(String.format("%s", trace.getTrace_likecount() > 0 ? trace.getTrace_likecount() : getString(R.string.btn_trace_like)));
        mBtnLike.setOnClickListener(v -> getPresenter().addLike());
        mBtnLike.setChecked(trace.is_like());
        mTvTime.setText(trace.getTrace_addtime());

        setRelation(trace);
        mRcvComment.setAdapter(getPresenter().getAdapter());
        getPresenter().getAdapter().addAll(trace.getComment_list());
    }

    public void setReplay(TraceComment comment) {
        mEtComment.setHint(String.format(getString(R.string.hint_trace_comment_replay), comment.getComment_membername()));
        mBtnAddComment.setOnClickListener(v -> getPresenter().replyComment(comment, mEtComment.getText().toString().trim()));
    }

    public void clearEditText() {
        LUtils.closeKeyboard(mEtComment);
        mEtComment.setText("");
        mBtnComment.setOnClickListener(v -> mEtComment.setHint(getString(R.string.hint_new_content)));
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
                mBtnFollow.setTextColor(getResources().getColor(R.color.colorPrimary));
                mBtnFollow.setBackgroundResource(R.drawable.btn_rectangle_primary_stroke);
                mBtnFollow.setOnClickListener(v -> getPresenter().addFollow());
                break;
            case 2 :
                mTvTime.setVisibility(View.GONE);
                mBtnFollow.setVisibility(View.VISIBLE);
                mBtnFollow.setText(R.string.btn_relation_friend);
                mBtnFollow.setTextColor(getResources().getColor(R.color.white));
                mBtnFollow.setBackgroundResource(R.drawable.btn_radius_primary2dark_selector);
                mBtnFollow.setOnClickListener(v -> getPresenter().delFollow());
                break;
            case 3 :
                supportInvalidateOptionsMenu();
                mTvTime.setVisibility(View.VISIBLE);
                mBtnFollow.setVisibility(View.GONE);
                break;
            case 4:
                mTvTime.setVisibility(View.GONE);
                mBtnFollow.setVisibility(View.VISIBLE);
                mBtnFollow.setText(R.string.btn_followed);
                mBtnFollow.setTextColor(getResources().getColor(R.color.white));
                mBtnFollow.setBackgroundResource(R.drawable.btn_radius_primary2dark_selector);
                mBtnFollow.setOnClickListener(v -> getPresenter().delFollow());
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete :
                getPresenter().delTrace();
                break;
            case R.id.action_inform:
                new AlertDialog.Builder(this)
                        .setItems(R.array.inform_content, (d, p) -> {
                            getPresenter().inform(getResources().getStringArray(R.array.inform_content)[p]);
                        }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trace, menu);
        if (getPresenter().getDataSubject().getValue() != null && getPresenter().getDataSubject().getValue().getRelation() == 3) {
            menu.findItem(R.id.action_delete).setVisible(true);
            menu.findItem(R.id.action_inform).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
