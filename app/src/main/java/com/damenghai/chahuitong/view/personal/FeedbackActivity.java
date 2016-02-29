package com.damenghai.chahuitong.view.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.fb.fragment.FeedbackFragment;

public class FeedbackActivity extends BaseActivity {

    private FeedbackFragment mFeedbackFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_feedback);
        setToolbarTitle(R.string.title_activity_feedback);

        if (savedInstanceState == null) {
            String conversation_id = getIntent().getStringExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID);
            LUtils.log("conversation_id: " + conversation_id);
            mFeedbackFragment = FeedbackFragment.newInstance(conversation_id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.feedback_container, mFeedbackFragment)
                    .commit();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mFeedbackFragment.onRefresh();
    }
}
