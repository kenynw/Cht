package com.damenghai.chahuitong.view.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.presenter.VoucherPresenter;
import com.damenghai.chahuitong.utils.L;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherFragment extends BaseListFragment implements VoucherMvpView {
    private static final String KEY_STATE = "BargainListFragment:state";

    private String mState;

    private List<Voucher> mList;

    private VoucherPresenter mPresenter;

    public VoucherFragment() {}

    public static VoucherFragment newInstance(String state) {
        VoucherFragment fragment = new VoucherFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_STATE, state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showList(List<Voucher> list) {
        getRecycleView().setAdapter(new VoucherListAdapter(mContext, list));
    }

    @Override
    public String getState() {
        return mState;
    }

    @Override
    public void loadData() {
        mPresenter.list();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = getArguments().getString(KEY_STATE);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new VoucherPresenter(mContext);
        mPresenter.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

    public class VoucherListAdapter extends RecyclerView.Adapter<VoucherListAdapter.VoucherViewHolder> {
        public Context mContext;

        public List<Voucher> mData;

        public VoucherListAdapter(Context context, List<Voucher> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public VoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_voucher, parent, false);
            return new VoucherViewHolder(view);
        }

        @Override
        public void onBindViewHolder(VoucherViewHolder holder, int position) {
            final Voucher voucher = mData.get(position);

            holder.mTvTitle.setText(voucher.getVoucher_title());
            holder.mTvValue.setText(voucher.getVoucher_price());
            holder.mTvValid.setText(voucher.getVoucher_start_date() + "~" + voucher.getVoucher_end_date());
            holder.mTvLimit.setText(voucher.getVoucher_limit());

            L.d(voucher.getVoucher_state());

            if (voucher.getVoucher_state().equals("1")) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(voucher);
                        ((Activity) mContext).finish();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class VoucherViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_voucher_title)
            TextView mTvTitle;

            @Bind(R.id.tv_voucher_price)
            TextView mTvValue;

            @Bind(R.id.tv_voucher_valid)
            TextView mTvValid;

            @Bind(R.id.tv_voucher_limit)
            TextView mTvLimit;

            public VoucherViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

}
