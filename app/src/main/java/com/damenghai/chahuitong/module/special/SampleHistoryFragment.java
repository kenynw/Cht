package com.damenghai.chahuitong.module.special;

import android.content.Context;

import com.damenghai.chahuitong.adapter.SampleListAdapter;
import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.presenter.SampleListPresenter;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SampleHistoryFragment extends BaseListFragment implements SampleListMvpView {

    private SampleListPresenter mSamplePresenter = new SampleListPresenter();;

    public SampleHistoryFragment() {
    }

    @Override
    public void loadData() {
        mSamplePresenter.showList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSamplePresenter.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSamplePresenter.detach();
    }

    @Override
    public void showList(List<Sample> list) {
        if (getRecycleView() != null) {
            getRecycleView().setAdapter(new SampleListAdapter(getActivity(), list));
        }
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError(String message) {
        showShort(message);
    }

}
