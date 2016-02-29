package com.damenghai.chahuitong.view.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
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
        mFlvRecently.setOnTextClickListener(v -> LUtils.toast(v));
    }
}
