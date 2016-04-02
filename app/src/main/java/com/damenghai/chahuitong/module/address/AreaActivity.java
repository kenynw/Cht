package com.damenghai.chahuitong.module.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.widget.WrapHeightListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class AreaActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.address_lv_selected)
    WrapHeightListView mListView;

    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mAreasString;

    private ArrayList<Area> mAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initView();
    }

    protected void initView() {
        addFragment("");

        mAreasString = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mAreasString);
        mListView.setAdapter(mAdapter);

        mAreas = new ArrayList<Area>();

        mListView.setOnItemClickListener(this);
    }

    public void onEventMainThread(Area area) {
        mAreas.add(area);

        if(mAreasString.size() == 2) {
            Intent data = new Intent();
            mAreasString.add(area.getArea_name());
            data.putExtra("cityId", mAreas.get(1).getArea_id());
            data.putExtra("areaId", mAreas.get(2).getArea_id());
            StringBuilder builder = new StringBuilder();
            for(String areaString : mAreasString) {
                builder.append(areaString + " ");
            }
            data.putExtra("area", builder.toString());
            setResult(Activity.RESULT_OK, data);
            finish();
        } else {
            mAreasString.add(area.getArea_name());
            mAdapter.notifyDataSetChanged();

            addFragment(area.getArea_id());
        }
    }

    public void removeItem(String areaId) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(areaId, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mAreasString.remove(areaId);
        mAdapter.notifyDataSetChanged();
    }

    private void addFragment(String areaId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AreaFragment fragment = AreaFragment.get(areaId);
        ft.replace(R.id.address_fragment, fragment);
        if(!TextUtils.isEmpty(areaId)) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        FragmentManager fm = getSupportFragmentManager();
        if(position == 0) {
            for(int i=0; i< mAreasString.size(); i++) {
                fm.popBackStackImmediate();
            }
            mAreasString.clear();
        } else {
            fm.popBackStackImmediate();
            mAreasString.remove(mAreasString.size() - 1);
        }
        mAdapter.notifyDataSetChanged();

//        String id = (String) adapterView.getItemAtPosition(position);
//        removeItem(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
            FragmentManager fm = getSupportFragmentManager();
            if(!fm.popBackStackImmediate()) {
                finish();
            } else {
                mAreasString.remove(mAreasString.size() - 1);
                mAdapter.notifyDataSetChanged();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
