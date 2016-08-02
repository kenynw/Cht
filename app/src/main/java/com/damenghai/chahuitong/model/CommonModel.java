package com.damenghai.chahuitong.model;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.UpdateInfo;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.settings.UpdateService;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CommonModel {

    private static CommonModel mInstance;

    public static CommonModel getInstance() {
        if (null == mInstance) {
            synchronized (CommonModel.class) {
                if (null == mInstance) mInstance = new CommonModel();
            }
        }
        return mInstance;
    }

    public void update(Context context) {
        ServiceClient.getServices().checkUpdate(LUtils.getAppVersionName())
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<UpdateInfo>() {
                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        if (updateInfo.isIf_update()) {
                            showUpdateDialog(
                                    context,
                                    updateInfo.getVersion(),
                                    updateInfo.getInfo(),
                                    updateInfo.getUrl()
                            );
                        }
                    }
                });
    }

    public void checkUpdate(Context context) {
        ServiceClient.getServices().checkUpdate(LUtils.getAppVersionName())
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<UpdateInfo>() {
                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        if (updateInfo.isIf_update()) {
                            showUpdateDialog(
                                    context,
                                    updateInfo.getVersion(),
                                    updateInfo.getInfo(),
                                    updateInfo.getUrl()
                            );
                        } else {
                            LUtils.toast(R.string.toast_not_update);
                        }
                    }
                });
    }

    public void showUpdateDialog(Context context, String versionName, String info, String url) {
        new AlertDialog.Builder(context)
                .setTitle("发现新版本" + versionName)
                .setMessage(info)
                .setCancelable(false)
                .setNegativeButton("不更新", null)
                .setPositiveButton("立即更新", (dialogInterface, i) -> {
                    Intent intent = new Intent(context, UpdateService.class);
                    intent.putExtra("title", "正在下载茶汇通");
                    intent.putExtra("url", url);
                    intent.putExtra("path", findDownLoadDirectory());
                    intent.putExtra("name", context.getString(R.string.app_name) + "v" + versionName + ".apk");
                    context.startService(intent);
                })
                .show();
    }

    private String findDownLoadDirectory(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            LUtils.log("找到SD卡");
            return Environment.getExternalStorageDirectory() + "/" + "download/";
        }else{
            LUtils.log("没有SD卡");
            return Environment.getRootDirectory() + "/" + "download/";
        }
    }

}
