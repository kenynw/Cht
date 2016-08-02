package com.damenghai.chahuitong.module.trace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TraceCommentAdapter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.AlbumImage;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.ImageBrowseActivity;
import com.damenghai.chahuitong.module.user.UserInfoActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceDetailPresenter extends BaseDataActivityPresenter<TraceDetailActivity, Trace> implements TextWatcher {
    private TraceCommentAdapter mAdapter;

    private int mTraceId;

    @Override
    protected void onCreate(TraceDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(TraceDetailActivity view) {
        super.onCreateView(view);
        mAdapter = new TraceCommentAdapter(view);
        mAdapter.setOnItemClickListener(position -> {
            TraceComment comment = mAdapter.getItem(position);
            if (comment.getRelation() == 1) {
                new AlertDialog.Builder(getView())
                        .setItems(new String[]{"删除"}, (dialogInterface, i) -> {
                            TraceModel.getInstance().delComment(comment.getComment_id()).subscribe(new ServiceResponse<String>() {
                                @Override
                                public void onNext(String s) {
                                    mAdapter.remove(comment);
                                }
                            });
                        }).show();
            } else {
                new AlertDialog.Builder(getView())
                        .setItems(new String[]{"回复", "举报"}, (dialogInterface, i) -> {
                            if (i == 0) {
                                getView().setReplay(comment);
                            } else {
                                new AlertDialog.Builder(getView())
                                        .setItems(R.array.inform_content, (d, p) -> {
                                            inform(getView().getResources().getStringArray(R.array.inform_content)[p]);
                                        }).show();
                            }
                        }).show();
            }
        });
        mTraceId = getView().getIntent().getIntExtra("trace_id", 0);
        loadData();
    }

    private void loadData() {
        TraceModel.getInstance().getTraceDetail(mTraceId)
                .doOnError(throwable -> getView().finish())
                .unsafeSubscribe(getDataSubscriber());
    }

    public void delTrace() {
        new AlertDialog.Builder(getView())
                .setTitle(R.string.dialog_title_delete_trace)
                .setMessage(R.string.dialog_delete_trace)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, (d, p) -> {
                    TraceModel.getInstance().delTrace(mTraceId).subscribe(new ServiceResponse<String>() {
                        @Override
                        public void onNext(String s) {
                            getView().finish();
                        }
                    });
                }).show();
    }

    public void addFollow() {
        FriendModel.getInstance().addFollow(getDataSubject().getValue().getTrace_memberid())
                .subscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer relation) {
                        Trace trace = getDataSubject().getValue();
                        trace.setRelation(relation);
                        getView().setRelation(trace);
                    }
                });
    }

    public void delFollow() {
        FriendModel.getInstance().delFollow(getDataSubject().getValue().getTrace_memberid())
                .subscribe(new ServiceResponse<Integer>() {
                    @Override
                    public void onNext(Integer s) {
                        Trace trace = getDataSubject().getValue();
                        trace.setRelation(1);
                        getView().setRelation(trace);
                    }
                });
    }

    public void addComment(String content) {
        TraceModel.getInstance().addComment(mTraceId, content)
                .subscribe(new ServiceResponse<TraceComment>() {
                    @Override
                    public void onNext(TraceComment comment) {
                        getView().clearEditText();
                        comment.setRelation(1);
                        mAdapter.insert(comment, 0);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void replyComment(TraceComment comment, String content) {
        TraceModel.getInstance().replyComment(comment.getComment_id(), content)
                .subscribe(new ServiceResponse<TraceComment>() {
                    @Override
                    public void onNext(TraceComment result) {
                        getView().clearEditText();
                        result.setComment_reply(comment);
                        mAdapter.insert(result, 0);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void addLike() {
        TraceModel.getInstance().addTraceLike(mTraceId).subscribe(new ServiceResponse<>());
    }

    public void inform(String content) {
        TraceModel.getInstance().informTrace(mTraceId, content).subscribe(new ServiceResponse<>());
    }

    public TraceCommentAdapter getAdapter() {
        return mAdapter;
    }

    public void showUserInfo(int userId) {
        Intent i = new Intent(getView(), UserInfoActivity.class);
        i.putExtra("userId", userId);
        getView().startActivity(i);
    }

    public void showImageDetail(ArrayList<AlbumImage> imageList) {
        Intent i = new Intent(getView(), ImageBrowseActivity.class);
        i.putParcelableArrayListExtra("images", imageList);
        getView().startActivity(i);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            getView().getSubmitBtn().setEnabled(true);
        } else {
            getView().getSubmitBtn().setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void share() {
        final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA};

        new ShareAction(getView())
                .setDisplayList(displayList)
                .withTitle(getDataSubject().getValue().getTrace_membername() + "的茶友圈动态")
                .withText(getDataSubject().getValue().getTrace_title())
                .withMedia(new UMImage(getView(), getDataSubject().getValue().getTrace_image()))
                .open();
    }
}
