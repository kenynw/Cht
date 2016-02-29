package com.damenghai.chahuitong.view.special;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.damenghai.chahuitong.adapter.BargainListAdapter;
import com.damenghai.chahuitong.base.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.presenter.BargainPresenter;
import com.damenghai.chahuitong.utils.L;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BargainListFragment extends BaseListFragment implements BargainMvpView {
    private static final String KEY_TAB = "BargainListFragment:tab";

    private static final String[] OP = new String[] {"current_api", "coming_api"};

    private BargainPresenter mPresenter;

    private int mTab;

    public BargainListFragment() {
        mPresenter = new BargainPresenter();
    }

    public static BargainListFragment newInstance(int tab) {
        BargainListFragment fragment = new BargainListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TAB, tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTab = getArguments().getInt(KEY_TAB);
        }
    }

    @Override
    public void loadData() {
        mPresenter.showList();
        setOnLastItemVisibleListener(mPresenter::showList);
    }

    @Override
    public void showList(List<Bargain> list) {
        if (getAdapter() != null && getAdapter() instanceof BargainListAdapter) {
            ((BargainListAdapter) getAdapter()).addList(list);
        } else {
            getRecycleView().setAdapter(new BargainListAdapter(mContext, list));
        }
    }

    @Override
    public String getOp() {
        return OP[mTab];
    }

    @Override
    public int getCurPage() {
        return getPage();
    }

    @Override
    public void showError(String message) {
        showShort(message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

}
