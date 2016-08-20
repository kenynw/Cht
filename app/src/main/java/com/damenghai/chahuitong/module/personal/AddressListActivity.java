package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.AddressViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(AddressListPresenter.class)
public class AddressListActivity extends BaseListActivity<AddressListPresenter> {

    @Bind(R.id.btn_address_add)
    Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_address);
        ButterKnife.bind(this);

        mBtnAdd.setOnClickListener(v -> getPresenter().showEditAddress());
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_address_list;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        AddressViewHolder holder = new AddressViewHolder(parent);
        holder.setOnOperationListener(getPresenter());
        return holder;
    }

}
