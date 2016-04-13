package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.module.mall.GoodsListActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.widget.FlowViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    String[] mRecently = new String[] {"普洱茶", "黄茶", "铁观音", "云顶","普洱茶", "黄茶", "铁观音", "云顶"};

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

        mFlvRecently.setText(mRecently);
        mFlvRecently.setOnTextClickListener(text -> {
            Intent intent = new Intent(this, GoodsListActivity.class);
            intent.putExtra("keyword", text);
            intent.putExtra("op", "goods_list");
            startActivity(intent);
        });
        mBtnDone.setOnClickListener(v -> {
            Intent intent = new Intent(this, GoodsListActivity.class);
            intent.putExtra("keyword", mEtSearch.getText());
            intent.putExtra("op", "goods_list");
            startActivity(intent);
        });
    }
}
