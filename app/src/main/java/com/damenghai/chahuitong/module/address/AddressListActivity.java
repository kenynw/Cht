package com.damenghai.chahuitong.module.address;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.BaseListAdapter;
import com.damenghai.chahuitong.adapter.ViewHolder;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.utils.DialogFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class AddressListActivity extends BaseActivity implements AddressListMvpView {

    @Bind(R.id.lv_address_list)
    ListView mListView;

    @Bind(R.id.address_list_container)
    FrameLayout mContainer;

    @Bind(R.id.address_list_layout)
    LinearLayout mListLayout;

    private AddAddressFragment mFragment;

    private String mState;

    private AddressListPresenter mPresenter;

    private AddressListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_address_list);
        setToolbarTitle("我的地址");
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        mState = getIntent().getStringExtra("state");

        initView();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_list, menu);
        return true;
    }

    private void initView() {
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mAdapter.getSelectedItems().size() > 0) {
                    DialogFactory.createGenericDialog(AddressListActivity.this,
                            R.string.dialog_delete_address, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mPresenter.delete();
                                }
                            }).show();
                }
                return true;
            }
        });
    }

    public void toAddAddress(View view) {
        mListLayout.setVisibility(View.GONE);
        mContainer.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mFragment = new AddAddressFragment();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.address_list_container, mFragment);
        ft.commit();
    }

    public void editAddress(Address address) {
        mListLayout.setVisibility(View.GONE);
        mContainer.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mFragment = AddAddressFragment.get(address);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.address_list_container, mFragment);
        ft.commit();
    }

    private void loadData() {
        mPresenter = new AddressListPresenter(this);
        mPresenter.attach(this);
        mPresenter.showList();
    }

    // 新增地址或编辑地址的回调
    public void onEventMainThread(Address address) {
        if (mContainer.getVisibility() == View.VISIBLE && mFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.remove(mFragment);
            ft.commit();

            mContainer.setVisibility(View.GONE);
            mListLayout.setVisibility(View.VISIBLE);
            if (mAdapter != null) {
                mAdapter.add(0, address);
            }
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        onEventMainThread(null);
        super.onBackPressed();
    }

    @Override
    public void showList(List<Address> list) {
        mAdapter = new AddressListAdapter(this, list, R.layout.item_list_address);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public List<String> getSelectedItems() {
        return mAdapter == null ? null : mAdapter.getSelectedItems();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        EventBus.getDefault().unregister(this);
    }

    private class AddressListAdapter extends BaseListAdapter<Address> {

        private List<String> mSelectedItem;

        public AddressListAdapter(Context context, List<Address> data, int resId) {
            super(context, data, resId);
            mSelectedItem = new ArrayList<>();
        }

        public List<String> getSelectedItems() {
            return mSelectedItem;
        }

        @Override
        public void convert(final ViewHolder holder, final Address address) {
            holder.setText(R.id.tv_address_name, address.getTrue_name())
                    .setText(R.id.tv_address_mobile, address.getMob_phone())
                    .setText(R.id.tv_address_detail, address.getArea_info() + address.getAddress());

            final CheckBox cb = holder.getView(R.id.cb_address_edit);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) mSelectedItem.add(address.getAddress_id());
                    else mSelectedItem.remove(address.getAddress_id());
                }
            });

            if(mState != null && mState.equals("choose")) {
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(address);
                        finish();
                    }
                });
            } else {
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb.setChecked(!cb.isChecked());
                    }
                });
            }

        }
    }

}
