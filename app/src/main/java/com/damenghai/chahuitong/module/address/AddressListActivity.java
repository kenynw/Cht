package com.damenghai.chahuitong.module.address;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.adapter.viewholder.AddressViewHolder;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_list, menu);
        return true;
    }


}
