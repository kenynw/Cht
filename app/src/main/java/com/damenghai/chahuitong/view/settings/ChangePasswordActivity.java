package com.damenghai.chahuitong.view.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ChangePasswordPresenter.class)
public class ChangePasswordActivity extends BeamBaseActivity<ChangePasswordPresenter> {

    @Bind(R.id.et_change_old)
    EditText mEtOld;

    @Bind(R.id.et_change_new)
    EditText mEtNew;

    @Bind(R.id.et_change_confirm)
    EditText mEtConfirm;

    @Bind(R.id.btn_change_ok)
    Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_change_password);
        setContentView(R.layout.setting_activity_change_passwd);
        ButterKnife.bind(this);
        mBtnOk.setOnClickListener(v -> commit());
    }

    private void commit() {
        if (TextUtils.isEmpty(mEtOld.getText())) {
            LUtils.toastLong(R.string.toast_old_password);
            return;
        }

        if (TextUtils.isEmpty(mEtNew.getText()) || mEtNew.length() < 8) {
            LUtils.toastLong(R.string.toast_new_password);
            return;
        }

        if (!mEtConfirm.getText().toString().equals(mEtNew.getText().toString())) {
            LUtils.toastLong(R.string.toast_invalid_confirm);
            return;
        }

        getPresenter().changePassword(mEtOld.getText(), mEtNew.getText());
    }

}
