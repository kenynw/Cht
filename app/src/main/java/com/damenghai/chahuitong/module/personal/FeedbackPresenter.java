package com.damenghai.chahuitong.module.personal;

import android.text.TextUtils;
import android.widget.Toast;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FeedbackPresenter extends Presenter<FeedbackActivity> {

    public void submit() {
        if (TextUtils.isEmpty(getView().getFeedback())) {
            LUtils.toast(R.string.toast_null_content);
        } else if (getView().getFeedback().length() < 5) {
            LUtils.toast(R.string.toast_invalid_content);
        } else {
            ServiceClient.getServices().feedbackAdd(LUtils.getPreferences().getString("key", ""),
                    getView().getFeedback(), getView().getContact())
                    .compose(new DefaultTransform<>())
                    .subscribe(new ServiceResponse<Integer>() {
                        @Override
                        public void onNext(Integer response) {
                            LUtils.toast(R.string.toast_feedback_success);
                            getView().finish();
                        }
                    });
        }
    }

}
