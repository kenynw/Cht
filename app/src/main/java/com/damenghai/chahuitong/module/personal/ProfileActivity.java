package com.damenghai.chahuitong.module.personal;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ProfilePresenter.class)
public class ProfileActivity extends BaseDataActivity<ProfilePresenter, User> {

    @Bind(R.id.ly_profile_avatar)
    LinearLayout mLyAvatar;

    @Bind(R.id.dv_profile_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.et_profile_name)
    EditText mEtName;

    @Bind(R.id.btn_profile_gender)
    Button mBtnGender;

    @Bind(R.id.btn_profile_born)
    Button mBtnBorn;

    @Bind(R.id.btn_profile_area)
    Button mBtnArea;

    @Bind(R.id.et_profile_intro)
    EditText mEtIntro;

    @Bind(R.id.btn_profile_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_profile);
        setToolbarTitle(R.string.title_activity_profile);
        ButterKnife.bind(this);

        mLyAvatar.setOnClickListener(v -> showSelectorDialog());
        mBtnGender.setOnClickListener(v -> getPresenter().showGender());
        mBtnBorn.setOnClickListener(v -> getPresenter().showBorn(mBtnBorn));
        mBtnArea.setOnClickListener(v -> getPresenter().showArea());
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    @Override
    public void setData(User user) {
        mDvAvatar.setImageURI(Uri.parse(user.getMember_avatar()));
        mEtName.setText(user.getMember_name());
        mBtnGender.setText(user.getMember_sex());
        mBtnBorn.setText(user.getMember_birthday());
        mBtnArea.setText(user.getMember_areainfo());
        mEtIntro.setText(user.getMember_intro());
    }

    private void checkInput() {
        if (mEtName.getText().toString().isEmpty()) {
            LUtils.toast("昵称不能为空");
            return;
        }
        if (mEtName.getText().length() < 3 || mEtName.getText().length() > 15) {
            LUtils.toast("昵称长度在3-15之间");
            return;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
        if (!pattern.matcher(mEtName.getText().toString().trim()).matches()) {
            LUtils.toast("昵称不能含有特殊字符");
            return;
        }

        User user = getPresenter().getDataSubject().getValue();
        user.setMember_name(mEtName.getText().toString().trim());
        user.setMember_birthday(mBtnBorn.getText().toString().trim());
        user.setMember_areainfo(mBtnArea.getText().toString().trim());
        user.setMember_intro(mEtIntro.getText().toString().trim());
        getPresenter().save(user);
    }

    public void setGender(int index) {
        mBtnGender.setText(index == 0 ? "男" : "女");
    }

    public void showSelectorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择图片来源")
                .setItems(new String[]{"拍照", "相册"}, (dialog, which) -> {
                    getPresenter().editFace(which);
                }).show();
    }

    public void setImage(Uri uri) {
        mDvAvatar.setImageURI(uri);
    }

    public void setAreaInfo(String areaInfo) {
        mBtnArea.setText(areaInfo);
    }

}
