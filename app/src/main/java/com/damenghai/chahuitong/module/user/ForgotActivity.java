package com.damenghai.chahuitong.module.user;

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

@RequiresPresenter(ForgotPresenter.class)
public class ForgotActivity extends BeamBaseActivity<ForgotPresenter> {

    @Bind(R.id.et_reset_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_reset_code)
    EditText mEtCode;

    @Bind(R.id.btn_reset_code)
    Button mBtnCode;

    @Bind(R.id.et_reset_password)
    EditText mEtPwd;

    @Bind(R.id.et_reset_confirm)
    EditText mEtConfirm;

    @Bind(R.id.btn_reset_ok)
    Button mBtnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_forgot);
        setToolbarTitle(R.string.title_activity_reset_password);
        ButterKnife.bind(this);
        mBtnCode.setOnClickListener(v -> sendCode());
        mBtnCommit.setOnClickListener(v -> commit());
    }

    private void sendCode() {
        if (TextUtils.isEmpty(mEtMobile.getText()) || mEtMobile.length() != 11) {
            LUtils.toast(R.string.toast_invalid_mobile);
            return;
        }

        getPresenter().sendCode(mEtMobile.getText());
    }

    private void commit() {
        if (TextUtils.isEmpty(mEtMobile.getText()) || mEtMobile.length() != 11) {
            LUtils.toastLong(R.string.toast_invalid_mobile);
            return;
        }

        if (TextUtils.isEmpty(mEtCode.getText()) || mEtCode.length() != 6) {
            LUtils.toastLong(R.string.toast_invalid_code);
            return;
        }

        if (TextUtils.isEmpty(mEtPwd.getText()) || mEtPwd.length() < 8) {
            LUtils.toastLong(R.string.toast_invalid_password);
            return;
        }

        if (!mEtConfirm.getText().toString().equals(mEtPwd.getText().toString())) {
            LUtils.toastLong(R.string.toast_invalid_confirm);
            return;
        }

        getPresenter().commit(mEtMobile.getText(), mEtCode.getText(), mEtPwd.getText());
    }

}
