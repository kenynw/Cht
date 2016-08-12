package com.damenghai.chahuitong.module.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ModifyPwdPresenter.class)
public class ModifyPwdActivity extends BeamBaseActivity<ModifyPwdPresenter> {

    @Bind(R.id.et_modify_pwd_old)
    EditText mEtOld;

    @Bind(R.id.et_modify_pwd_new)
    EditText mEtNew;

    @Bind(R.id.et_modify_pwd_confirm)
    EditText mEtConfirm;

    @Bind(R.id.btn_modify_pwd_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_modify_pwd);
        setToolbarTitle(R.string.title_activity_modify_pwd);
        ButterKnife.bind(this);
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
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

        getPresenter().commit(
                mEtOld.getText().toString().trim(),
                mEtNew.getText().toString().trim(),
                mEtConfirm.getText().toString().trim()
        );
    }

}
