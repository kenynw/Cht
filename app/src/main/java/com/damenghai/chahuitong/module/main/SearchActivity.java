package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.widget.FlowViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SearchPresenter.class)
public class SearchActivity extends BaseDataActivity<SearchPresenter, String[]> {

    @Bind(R.id.et_search_content)
    EditText mEtSearch;

    @Bind(R.id.btn_search_done)
    Button mBtnDone;

    @Bind(R.id.flv_search_recently)
    FlowViewGroup mFlvRecently;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_search);
        ButterKnife.bind(this);

        mFlvRecently.setOnTextClickListener(text -> getPresenter().showGoodsList(text));
        mBtnDone.setOnClickListener(v ->getPresenter().showGoodsList(mEtSearch.getText().toString().trim()));
        mEtSearch.setOnEditorActionListener((v, actionId, event) -> {
            getPresenter().showGoodsList(mEtSearch.getText().toString().trim());
            return false;
        });
    }

    @Override
    public void setData(String[] strings) {
        mFlvRecently.setText(strings);
    }
}
