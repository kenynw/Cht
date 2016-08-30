package com.damenghai.chahuitong.module.personal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.UserModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ProfilePresenter extends BaseDataActivityPresenter<ProfileActivity, User> implements OnImageSelectListener {

    private ImageProvider mProvider;

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getProfile().subscribe(getDataSubscriber());
    }

    public void save(User user) {
        UserModel.getInstance().updateProfile(user).subscribe(new ServiceResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                getView().finish();
            }
        });
    }

    public void editFace(int position) {
        OnImageSelectListener listener = new OnImageSelectListener() {
            @Override
            public void onImageSelect() {

            }

            @Override
            public void onImageLoaded(Uri uri) {
                mProvider.corpImage(uri, 120, 120, ProfilePresenter.this);
            }

            @Override
            public void onError() {

            }
        };

        switch (position) {
            case 0 :
                mProvider.getImageFromCamera(listener);
                break;
            case 1 :
                mProvider.getImageFromAlbum(listener, 1);
                break;
        }
    }

    public void showGender() {
        new AlertDialog.Builder(getView())
                .setItems(new String[]{"男", "女"}, (dialog1, which) -> {
                    getView().setGender(which);
                    getDataSubject().getValue().setMember_sex(String.valueOf(which + 1));
                })
                .show();
    }

    public void showBorn(TextView view) {
//        LUtils.toast("lll");
//        String date1 = view.getText().toString();
//        DateUtils.showDateDialog(getView(), date1, (view1, year, monthOfYear, dayOfMonth)
//                -> view.setText(String.format("%s-%02d-%s", year, monthOfYear + 1, dayOfMonth)));
//
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date date = format.parse(view.getText().toString().trim().isEmpty() ? "1970-01-01" : view.getText().toString().trim());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            DatePickerDialog dialog = new DatePickerDialog(getView(), (datePicker, year, monthOfYear, dayOfMonth) -> {
                view.setText(String.format("%s-%02d-%s", year, monthOfYear + 1, dayOfMonth));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void showArea() {
        Intent i = new Intent(getView(), AreaActivity.class);
        i.putExtra("deep", 2);
        i.putExtra("action", "com.cht.profile");
        getView().startActivity(i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Area area = intent.getParcelableExtra("area");
        getView().setAreaInfo(area.getArea_name());
        getDataSubject().getValue().setMember_areaid(area.getArea_id());
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        mProvider.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageLoaded(Uri uri) {
        getView().getExpansionDelegate().showProgressBar();

        UserModel.getInstance().uploadAvatar(new File(uri.getPath()))
                .finallyDo(() -> getView().getExpansionDelegate().hideProgressBar())
                .subscribe(new ServiceResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        LUtils.toast(R.string.toast_upload_success);
                        getView().setImage(uri);
                    }
                });
    }

    @Override
    public void onImageSelect() {

    }

    @Override
    public void onError() {

    }

}
