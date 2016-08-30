package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends BeamBaseActivity<LoginPresenter> implements TextWatcher, View.OnFocusChangeListener {

    @Bind(R.id.et_login_username)
    EditText mEtUsername;

    @Bind(R.id.btn_username_clear)
    ImageButton mBtnClearName;

    @Bind(R.id.et_login_password)
    EditText mEtPassword;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.btn_login_register)
    Button mBtnRegister;

    @Bind(R.id.btn_login_forgot)
    Button mBtnForgot;

    @Bind(R.id.btn_login_feedback)
    Button mBtnFeedback;

    @Bind(R.id.tv_login_qq)
    Button mBtnQQ;

    @Bind(R.id.tv_login_weibo)
    Button mBtnWeibo;

    @Bind(R.id.tv_login_weixin)
    Button mBtnWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
        setToolbarTitle(R.string.title_activity_login);
        ButterKnife.bind(this);

        mEtUsername.setText(LUtils.getPreferences().getString("mobile", ""));
        mEtUsername.addTextChangedListener(this);
        mEtUsername.setOnFocusChangeListener(this);
        mBtnClearName.setOnClickListener(v -> mEtUsername.setText(""));

        mBtnLogin.setOnClickListener(v -> checkInput());
        mBtnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        mBtnForgot.setOnClickListener(v -> startActivity(new Intent(this, ForgotActivity.class)));
        mBtnFeedback.setOnClickListener(v -> startActivity(new Intent(this, FeedbackActivity.class)));
        mBtnQQ.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.QQ));
        mBtnWeibo.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.SINA));
        mBtnWeixin.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.WEIXIN));
    }

    public void checkInput() {
        if (TextUtils.isEmpty(mEtUsername.getText().toString().trim())) {
            LUtils.toast(R.string.toast_null_mobile);
            return;
        }
        if (mEtUsername.getText().toString().trim().length() != 11) {
            LUtils.toast(R.string.toast_invalid_mobile);
            return;
        }
        if (TextUtils.isEmpty(mEtPassword.getText().toString().trim())) {
            LUtils.toast(R.string.toast_null_password);
            return;
        }
        if (mEtPassword.getText().toString().length() < 6) {
            LUtils.toast(R.string.toast_invalid_password);
            return;
        }
        getPresenter().login(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString().trim());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            mBtnClearName.setVisibility(View.VISIBLE);
        } else {
            mBtnClearName.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b && mEtUsername.length() > 0) {
            mBtnClearName.setVisibility(View.VISIBLE);
        } else {
            mBtnClearName.setVisibility(View.GONE);
        }
    }
}
