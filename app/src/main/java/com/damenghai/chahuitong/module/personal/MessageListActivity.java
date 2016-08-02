package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.MessageViewHolder;
import com.damenghai.chahuitong.adapter.viewholder.MsgFansViewHolder;
import com.damenghai.chahuitong.adapter.viewholder.MsgSystemViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;

@RequiresPresenter(MessageListPresenter.class)
public class MessageListActivity extends BaseListActivity<MessageListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarTitle(getIntent().getStringExtra("title"));
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        switch (getIntent().getIntExtra("type", 0)) {
            case MessageListPresenter.TYPE_FANS :
                return new MsgFansViewHolder(parent);
            case MessageListPresenter.TYPE_SYSTEM :
                return new MsgSystemViewHolder(parent);
            default :
                return new MessageViewHolder(parent);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.message_activity_list;
    }
}
