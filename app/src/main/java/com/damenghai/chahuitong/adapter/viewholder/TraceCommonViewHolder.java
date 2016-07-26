package com.damenghai.chahuitong.adapter.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.ImageBrowseActivity;
import com.damenghai.chahuitong.module.trace.TraceDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class TraceCommonViewHolder extends BaseViewHolder<Trace> {

    @Bind(R.id.tv_trace_time)
    TextView mTvTime;

    @Bind(R.id.et_trace_content)
    TextView mTvContent;

    @Bind(R.id.dv_trace_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.tv_trace_img_count)
    TextView mTvImgNum;

    @Bind(R.id.btn_trace_share)
    Button mBtnShare;

    @Bind(R.id.btn_trace_comment)
    Button mBtnComment;

    @Bind(R.id.btn_trace_like)
    Button mBtnLike;

    public TraceCommonViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Trace trace) {
        itemView.setOnClickListener(v -> toDetail(trace));

        mTvTime.setText(trace.getTrace_addtime());
        mTvContent.setText(Html.fromHtml(trace.getTrace_title()));
        mDvImage.setImageURI(Uri.parse(trace.getTrace_image()));
        mDvImage.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ImageBrowseActivity.class);
            i.putParcelableArrayListExtra("images", trace.getTrace_image_list());
            getContext().startActivity(i);
        });
        if (trace.getTrace_image_list() != null && trace.getTrace_image_list().size() > 1) {
            mTvImgNum.setText(String.valueOf(trace.getTrace_image_list().size()));
        } else {
            mTvImgNum.setVisibility(View.GONE);
        }
        mBtnShare.setOnClickListener(v -> share(trace));
        mBtnLike.setOnClickListener(v -> TraceModel.getInstance().addTraceLike(trace.getTrace_id())
                .subscribe(new ServiceResponse<String>() {
                    @Override
                    public void onNext(String s) {
                        mBtnLike.setText(String.format(getContext().getString(R.string.btn_trace_like), trace.getTrace_likecount() + 1));
                    }
                }));
        mBtnLike.setText(String.format(getContext().getString(R.string.btn_trace_like), trace.getTrace_likecount() > 0 ? trace.getTrace_likecount() : ""));
        mBtnComment.setText(String.format(getContext().getString(R.string.btn_trace_comment), trace.getTrace_commentcount() > 0 ? trace.getTrace_commentcount() : ""));
        mBtnComment.setOnClickListener(v -> toDetail(trace));
    }

    private void toDetail(Trace trace) {
        Intent i = new Intent(getContext(), TraceDetailActivity.class);
        i.putExtra("trace_id", trace.getTrace_id());
        getContext().startActivity(i);
    }

    private void share(Trace trace) {
        final SHARE_MEDIA[] displayList = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA};

        new ShareAction((Activity) getContext())
                .setDisplayList(displayList)
                .withTitle(trace.getTrace_membername() + "的茶友圈动态")
                .withText(trace.getTrace_title())
                .withMedia(new UMImage(getContext(), trace.getTrace_image()))
                .open();
    }

}
