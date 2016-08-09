package com.damenghai.chahuitong.module.user;

import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.TraceHomeViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(UserInfoPresenter.class)
public class UserInfoActivity extends BaseListActivity<UserInfoPresenter> {

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new TraceHomeViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_detail;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerEmptyAble(false);
    }

    //
//    public void setRelation(int relation) {
//        switch (relation) {
//            case 1 :
//                btnFollow.setText(R.string.btn_add_follow);
//                btnFollow.setOnClickListener(v -> getPresenter().getPeople());
//                break;
//            case 2 :
//                btnFollow.setText(R.string.btn_relation_friend);
//                break;
//            case 3:
//                btnFollow.setText(R.string.btn_edit);
//            default :
//                break;
//        }
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_inform :
                new AlertDialog.Builder(this)
                        .setItems(R.array.inform_content, (d, p) -> {
                            getPresenter().inform(getResources().getStringArray(R.array.inform_content)[p]);
                        }).show();
                break;
            case R.id.action_block :

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getPresenter().getPeople() != null && getPresenter().getPeople().getRelation() != 3) getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
