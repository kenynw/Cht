package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FeedbackPresenter.class)
public class FeedbackActivity extends BeamBaseActivity<FeedbackPresenter> {

    @Bind(R.id.et_feedback_content)
    EditText mEtContent;

    @Bind(R.id.et_feedback_contact)
    EditText mEtContact;

    @Bind(R.id.btn_feedback_submit)
    Button mBtnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_feedback);
        ButterKnife.bind(this);
        setToolbarTitle(R.string.title_activity_feedback);
        mBtnSubmit.setOnClickListener(v -> getPresenter().submit());
    }

    public String getFeedback() {
        return mEtContent.getText().toString();
    }

    public String getContact() {
        return mEtContact.getText().toString();
    }

}
