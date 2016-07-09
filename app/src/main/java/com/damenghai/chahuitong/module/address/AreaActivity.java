package com.damenghai.chahuitong.module.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Area;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class AreaActivity extends BaseActivity {

    private StringBuilder mAreaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity_area);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        mAreaInfo = new StringBuilder();

        addFragment(0);
    }

    public void onEventMainThread(Area area) {
        if (area != null) {
            mAreaInfo.append(area.getArea_name());
            if(area.getArea_deep() == 3) {
                Intent data = new Intent();
                data.putExtra("area", area);
                data.putExtra("area_info", mAreaInfo.toString());
                setResult(Activity.RESULT_OK, data);
                finish();
            } else {
                addFragment(area.getArea_id());
            }
        }
    }

    private void addFragment(int areaId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AreaFragment fragment = AreaPresenter.newFragment(areaId);
        ft.replace(R.id.address_fragment, fragment);
        if(areaId > 0) {
            ft.addToBackStack(null);
        }
        ft.commit();
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
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
