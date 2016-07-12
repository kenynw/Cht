package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.FleaConsultAdapter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaDetailPresenter extends BaseDataActivityPresenter<FleaDetailActivity, Flea> implements TextWatcher {

    private int mFleaId;

    private FleaConsultAdapter mConsultAdapter;

    private int mConsultPage;

    @Override
    protected void onCreate(FleaDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mFleaId = getView().getIntent().getIntExtra("flea_id", 0);
    }

    @Override
    protected void onCreateView(FleaDetailActivity view) {
        super.onCreateView(view);
        FleaModel.getInstance().getFleaDetail(mFleaId).subscribe(getDataSubscriber());
        mConsultPage = 2;
    }

    public void loadMoreConsult() {
        FleaModel.getInstance().getConsultList(mConsultPage, mFleaId).subscribe(new ServiceResponse<BeanList<Consult>>(){
            @Override
            public void onNext(BeanList<Consult> consultBeanList) {
                super.onNext(consultBeanList);
                mConsultPage++;
                mConsultAdapter.addList(consultBeanList.getList());
            }
        });
    }

    public FleaConsultAdapter getAdapter(List<Consult> consultList) {
        if (mConsultAdapter == null) mConsultAdapter = new FleaConsultAdapter(getView(), consultList);
        return mConsultAdapter;
    }

    public void publishConsult(String content) {
        FleaModel.getInstance().saveConsult(content, mFleaId).subscribe(new ServiceResponse<Consult>() {
            @Override
            public void onNext(Consult consult) {
                LUtils.toast(R.string.toast_operate_success);
                LUtils.closeKeyboard(getView().getEtConsult());
                getView().getEtConsult().setText("");
                mConsultAdapter.add(0, consult);
            }
        });
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            getView().getBtnPublish().setEnabled(true);
        } else {
            getView().getBtnPublish().setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}
