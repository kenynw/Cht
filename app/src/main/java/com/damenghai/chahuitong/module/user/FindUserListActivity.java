package com.damenghai.chahuitong.module.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.UserViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FindUserListPresenter.class)
public class FindUserListActivity extends BaseListActivity<FindUserListPresenter> {

    @Bind(R.id.et_user_search)
    EditText mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_find_list;
    }

    public String getName() {
        return mEtSearch.getText().toString();
    }

    public EditText getEtSearch() {
        return mEtSearch;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerEmptyAble(false).setContainerErrorAble(false);
    }
}
