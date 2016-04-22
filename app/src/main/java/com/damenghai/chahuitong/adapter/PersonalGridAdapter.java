package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.utils.T;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.module.web.WebViewActivity;
import com.damenghai.chahuitong.module.personal.FavoritesActivity;
import com.damenghai.chahuitong.module.personal.VoucherActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PersonalGridAdapter extends RecyclerView.Adapter<PersonalGridAdapter.PersonalItemViewHolder> {

    private final int[] ICON_RES_LIST = new int[]{
            R.mipmap.ic_personal_favorites, R.mipmap.ic_personal_voucher,
            R.mipmap.ic_personal_phone, R.mipmap.ic_personal_service,
            R.mipmap.ic_personal_active, R.mipmap.ic_personal_feedback
    };

    private final int[] TEXT_RES_LIST = new int[] {
            R.string.btn_personal_favorites, R.string.btn_personal_voucher,
            R.string.btn_personal_phone, R.string.btn_personal_service,
            R.string.btn_personal_active, R.string.btn_personal_feedback,
    };

    private Context mContext;

    private String mKey;

    public PersonalGridAdapter(Context context) {
        mContext = context;
        mKey = new PreferenceHelper(mContext).readSession();
    }

    @Override
    public void onBindViewHolder(PersonalItemViewHolder holder, final int position) {
        holder.mIcon.setImageResource(ICON_RES_LIST[position]);
        holder.mText.setText(TEXT_RES_LIST[position]);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(mKey)) {
                intent.setClass(mContext, LoginActivity.class);
                mContext.startActivity(intent);
                return;
            }
            switch (position) {
                case 0 :
                    intent.setClass(mContext, FavoritesActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 1 :
                    intent.setClass(mContext, VoucherActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 2 :
                    intent.setClass(mContext, WebViewActivity.class);
                    intent.putExtra("url", "http://114.215.108.10:30001/Web/CHT/index.html");
                    mContext.startActivity(intent);
                    break;
                case 3 :
                    LUtils.toast("程序狗们正在努力开发中...");
                    break;
                case 4 :
                    LUtils.toast("程序狗们正在努力开发中...");
                    break;
                case 5 :
                    String id = new FeedbackAgent(mContext).getDefaultConversation().getId();
                    Intent feedback = new Intent(mContext, FeedbackActivity.class);
                    feedback.putExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, id);
                    mContext.startActivity(feedback);
                    break;

            }
        });
    }

    @Override
    public PersonalItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_personal, parent, false);
        view.setMinimumHeight(parent.getHeight() / 2 - 2);
        return new PersonalItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return ICON_RES_LIST.length;
    }

    public class PersonalItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_personal_icon)
        ImageView mIcon;

        @Bind(R.id.tv_personal_text)
        TextView mText;

        public PersonalItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
