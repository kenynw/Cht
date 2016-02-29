package com.damenghai.chahuitong.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.presenter.LoginPresenter;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.main.MainActivity;
import com.damenghai.chahuitong.view.personal.FeedbackActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Bind(R.id.et_login_username)
    EditText mEtUsername;

    @Bind(R.id.et_login_password)
    EditText mEtPassword;

    private UMShareAPI mShareApi;

    // TODO 登录成功后把数据缓存下来，这是业务应该放在P层
    private PreferenceHelper mPreferenceHelper;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
        setToolbarTitle(R.string.title_activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenter();
        mPresenter.attach(this);

        init();
    }

    private void init() {
        mShareApi = UMShareAPI.get(this);

        // TODO 登录成功应该只进行UI操作，不应该涉及数据库，后期就把缓存放入P层
        mPreferenceHelper = new PreferenceHelper(this);
        if (!TextUtils.isEmpty(mPreferenceHelper.readString("username"))) {
            mEtUsername.setText(mPreferenceHelper.readString("username"));
        }
    }

    @OnClick(R.id.tv_login_qq)
    void toQQLogin() {
        mShareApi.doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, mPresenter.doOauthListener());
    }

    @OnClick(R.id.tv_login_weibo)
    void toWeiboLogin() {
        mShareApi.doOauthVerify(LoginActivity.this, SHARE_MEDIA.SINA, mPresenter.doOauthListener());
    }

    @OnClick(R.id.tv_login_weixin)
    void toWeChatLogin() {
        mShareApi.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, mPresenter.doOauthListener());
    }

    public void toRegister(View view) {
        openActivity(RegisterActivity.class);
    }

    public void toSetPassword(View view) {
        openActivity(ForgotActivity.class);
    }

    public void toLogin(View view) {
        mPresenter.login();
    }

    public void toFeedback(View view) {
        String id = new FeedbackAgent(this).getDefaultConversation().getId();
        Intent feedback = new Intent(this, FeedbackActivity.class);
        feedback.putExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, id);
        startActivity(feedback);
    }

    @Override
    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void loginSuccess(Account user) {
        if (user == null) return;
        LUtils.toast(getResources().getString(R.string.toast_login_success));
        mPreferenceHelper.writeSession(user.getKey());
        mPreferenceHelper.writeValue("name", user.getUser_name());
        mPreferenceHelper.writeValue("username", user.getMobile());
        mPreferenceHelper.writeValue("key", user.getKey().replace("\"", ""));
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        mShareApi.onActivityResult(requestCode, resultCode, data);
    }

}
