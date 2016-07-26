package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

    RegisterPresenter mPresenter;

    @Bind(R.id.et_register_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_register_password)
    EditText mEtPassword;

    @Bind(R.id.et_register_captcha)
    EditText mEtCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbarTitle(R.string.title_activity_register);
        ButterKnife.bind(this);

        mPresenter = new RegisterPresenter();
        mPresenter.attach(this);
    }

    public void toFeedback(View view) {
        openActivity(FeedbackActivity.class);
    }

    @Override
    public String getMobileNum() {
        return mEtMobile.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public String getLabels() {
        return "";
    }

    @Override
    public String getCode() {
        return mEtCaptcha.getText().toString();
    }

    @Override
    public void sendCodeSuccess() {
        showShort("发送验证码成功");
    }

    @Override
    public void registerSuccess() {
        finish();
    }

    @Override
    @OnClick(R.id.btn_register_caption)
    public void sendCode() {
        mPresenter.sendCode();
    }

    @Override
    @OnClick(R.id.btn_register_submit)
    public void register() {
        mPresenter.register();
    }

    @OnClick(R.id.btn_register_feedback)
    public void feedback() {
        String id = new FeedbackAgent(this).getDefaultConversation().getId();
        Intent feedback = new Intent(this, FeedbackActivity.class);
        feedback.putExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, id);
        startActivity(feedback);
    }

    @Override
    public void showLoading() {
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
    }

    @Override
    public void showError(String message) {
        showShort(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
