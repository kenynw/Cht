package com.damenghai.chahuitong.module.personal;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.MemberModel;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.address.AreaActivity;
import com.damenghai.chahuitong.utils.DateUtils;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.Gson;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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
        MemberModel.getInstance().getMemberInfo().subscribe(getDataSubscriber());
    }

    public void save(User user) {
        MemberModel.getInstance().updateProfile(user).subscribe(new ServiceResponse<Boolean>() {
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
        String date = view.getText().toString();
        DateUtils.showDateDialog(getView(), date, (view1, year, monthOfYear, dayOfMonth)
                -> view.setText(String.format("%s-%02d-%s", year, monthOfYear + 1, dayOfMonth)));
    }

    public void showArea() {
        Intent i = new Intent(getView(), AreaActivity.class);
        i.putExtra("deep", 2);
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

        MemberModel.getInstance().uploadAvatar(new File(uri.getPath()))
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
