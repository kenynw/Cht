package com.damenghai.chahuitong.module.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.base.BaseActivity;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.presenter.ProfilePresenter;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity implements UserMvpView {

    private static final String[] ITEMS_GENDER = new String[] {"男", "女"};

    @Bind(R.id.sdv_profile_avatar)
    SimpleDraweeView mSdvAvatar;

    @Bind(R.id.tv_profile_username)
    TextView mTvUsername;

    @Bind(R.id.et_profile_truename)
    TextView mEtTruename;

    @Bind(R.id.tv_profile_gender)
    TextView mTvGender;

    @Bind(R.id.tv_profile_born)
    TextView mTvBorn;

    private ProfilePresenter mPresenter;

    private String mAvatarBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_profile);
        setToolbarTitle(R.string.title_activity_profile);
        ButterKnife.bind(this);

        mPresenter = new ProfilePresenter(this);
        mPresenter.attach(this);
        mPresenter.showUserInfo();
    }

    public void toChangeGender(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setItems(ITEMS_GENDER, (dialog1, which) -> mTvGender.setText(ITEMS_GENDER[which]));
        dialog.show();
    }

    public void toChangeBorn(View view) {
        String dateStr = mTvBorn.getText().toString();
        DateUtils.showDateDialog(this, dateStr, (view1, year, monthOfYear, dayOfMonth)
                -> mTvBorn.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth));
    }

    public void toSave(View view) {
        mPresenter.updateUserInfo();
    }

    public void toPickPicture(View view) {
        ImageUtils.showImagePickDialog(this);
    }

    public void toChangePassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case  ImageUtils.GALLERY_REQUEST_CODE :
                    if (data == null) return;
                    ImageUtils.showZoomImage(ProfileActivity.this, data.getData());
                    break;
                case ImageUtils.CAMERA_REQUEST_CODE :
                    ImageUtils.showZoomImage(ProfileActivity.this);
                    break;
                case ImageUtils.ZOOM_REQUEST_CODE :
                    Bitmap bitmap = data.getParcelableExtra("data");
                    if (bitmap != null) {
                        mAvatarBase64 = ImageUtils.bitmapToBase64(bitmap);
                        mSdvAvatar.setImageBitmap(bitmap);
                    }
                    break;
            }
        } else {
            ImageUtils.deleteImageUri(this);
        }
    }

    @Override
    public void setUser(User user) {
        mSdvAvatar.setImageURI(Uri.parse(user.getMember_avatar()));
        mTvBorn.setText(user.getMember_birthday());
        mTvGender.setText(user.getMember_sex());
        mTvUsername.append(user.getMember_name());
        mEtTruename.setText(user.getMember_truename());
    }

    @Override
    public String getAvatarBase64() {
        return mAvatarBase64;
    }

    @Override
    public String getTrueName() {
        return mEtTruename.getText().toString();
    }

    @Override
    public String getGender() {
        return mTvGender.getText().toString();
    }

    @Override
    public String getBirthday() {
        return mTvBorn.getText().toString();
    }
}
