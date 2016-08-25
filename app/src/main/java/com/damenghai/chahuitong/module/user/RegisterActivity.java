package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.damenghai.chahuitong.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(RegisterPresenter.class)
public class RegisterActivity extends BeamBaseActivity<RegisterPresenter> {

    @Bind(R.id.et_register_mobile)
    EditText mEtMobile;

    @Bind(R.id.btn_register_caption)
    Button mBtnCode;

    @Bind(R.id.et_register_captcha)
    EditText mEtCaptcha;

    @Bind(R.id.et_register_password)
    EditText mEtPassword;

    @Bind(R.id.btn_register_submit)
    Button mBtnSave;

    @Bind(R.id.btn_register_feedback)
    Button mBtnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);
        setToolbarTitle(R.string.title_activity_register);
        ButterKnife.bind(this);

        mBtnCode.setOnClickListener(v -> checkMobile());
        mBtnSave.setOnClickListener(v -> checkInput());
        mBtnFeedback.setOnClickListener(v -> startActivity(new Intent(this, FeedbackActivity.class)));
    }

    public void checkMobile() {
        if (mEtMobile.getText().toString().isEmpty() || mEtMobile.getText().length() != 11) {
            LUtils.toast(R.string.toast_invalid_mobile);
        }
        getPresenter().sendCode(mEtMobile.getText().toString().trim());
    }

    public void checkInput() {
        if (mEtMobile.getText().toString().isEmpty() || mEtMobile.getText().length() != 11) {
            LUtils.toast(R.string.toast_invalid_mobile);
        }
        if (mEtCaptcha.getText().toString().isEmpty() || mEtMobile.getText().length() != 6) {
            LUtils.toast(R.string.toast_invalid_code);
        }
        if (mEtPassword.getText().toString().isEmpty() || mEtMobile.getText().length() < 8) {
            LUtils.toast(R.string.toast_invalid_password);
        }
        getPresenter().register(
                mEtMobile.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim(),
                mEtPassword.getText().toString().trim()
        );
    }

}
