package com.damenghai.chahuitong.module.flea;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.utils.ImagePieceView;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.utils.DraweePieceView;
import com.jude.exgridview.PieceViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FleaAddPresenter.class)
public class FleaAddActivity extends BaseDataActivity<FleaAddPresenter, Flea> {

    @Bind(R.id.et_flea_title)
    EditText mEtTitle;

    @Bind(R.id.et_flea_detail)
    EditText mEtDetail;

    @Bind(R.id.pv_flea_image)
    PieceViewGroup mPvImage;

    @Bind(R.id.btn_flea_cate)
    Button mBtnCate;

    @Bind(R.id.et_flea_price)
    EditText mEtPrice;

    @Bind(R.id.et_flea_unit)
    EditText mEtUnit;

    @Bind(R.id.check_flea_no_price)
    CheckBox mCbNoPrice;

    @Bind(R.id.et_flea_tag)
    EditText mEtTag;

    @Bind(R.id.et_flea_phone)
    EditText mEtPhone;

    @Bind(R.id.et_flea_contact)
    EditText mEtContact;

    @Bind(R.id.btn_flea_location)
    Button mBtnLocation;

    @Bind(R.id.btn_flea_publish)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flea_activity_add);
        setToolbarTitle(R.string.title_publish);
        ButterKnife.bind(this);

        mPvImage.setOnAskViewListener(this::showSelectorDialog);
        mPvImage.setOnViewDeleteListener(getPresenter());
        mBtnCate.setOnClickListener(v -> getPresenter().showCate());
        mBtnLocation.setOnClickListener(v -> getPresenter().showArea());
        mBtnSave.setOnClickListener(v -> checkInput());
        mCbNoPrice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mEtPrice.setVisibility(View.GONE);
                mEtUnit.setVisibility(View.GONE);
            } else {
                mEtPrice.setVisibility(View.VISIBLE);
                mEtUnit.setVisibility(View.VISIBLE);
            }
            getPresenter().setIsQuotation(!isChecked);
        });
    }

    @Override
    public void setData(Flea flea) {
        mEtTitle.setText(flea.getGoods_name());
        mEtDetail.setText(flea.getGoods_body());
        mBtnCate.setText(flea.getGc_name());
        mEtPrice.setText(String.format("%s", flea.getGoods_store_price()));
        mEtTag.setText(flea.getGoods_tag());
        mEtPhone.setText(flea.getFlea_pphone());
        mEtContact.setText(flea.getFlea_pname());
        mBtnLocation.setText(flea.getFlea_area_name());

        if (flea.getDesc_image() != null && flea.getDesc_image().size() > 0) {
            for (int i=0; i<flea.getDesc_image().size(); i++) {
                FleaImage fleaImage = flea.getDesc_image().get(i);
                getPresenter().addImage(fleaImage);
                addImage(Uri.parse(fleaImage.getThumb_small()));
            }
            mPvImage.setOnAskViewListener(() -> LUtils.toast("暂不支持编辑图片"));
        }
        if (flea.getGoods_store_price() == 0) {
            mCbNoPrice.setChecked(true);
        }
    }

    public void checkInput() {
        if (mEtTitle.getText().toString().trim().isEmpty()) {
            LUtils.toast("标题不能为空");
            return;
        }
        if (!mCbNoPrice.isChecked()) {
            if (mEtPrice.getText().toString().trim().isEmpty()) {
                LUtils.toast("输个价格吧");
                return;
            }
        }

        Flea flea = new Flea();
        flea.setGoods_name(mEtTitle.getText().toString().trim());
        flea.setGoods_body(mEtDetail.getText().toString().trim());
        flea.setGoods_store_price(Double.valueOf(mEtPrice.getText().toString().trim()));
        flea.setGoods_tag(mEtTag.getText().toString().trim());
        flea.setFlea_pphone(mEtPhone.getText().toString().trim());
        flea.setFlea_pname(mEtContact.getText().toString().trim());

        getPresenter().saveFlea(flea);
    }

    public void showSelectorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择图片来源")
                .setItems(new String[]{"拍照", "相册"}, (dialog, which) -> {
                    getPresenter().editFace(which);
                }).show();
    }

    public void setArea(String area) {
        mBtnLocation.setText(area);
    }

    public void setCate(String cate) {
        mBtnCate.setText(cate);
    }

    public void addImage(Bitmap bitmap) {
        ImagePieceView pieceView = new ImagePieceView(this);
        pieceView.setImageBitmap(bitmap);
        mPvImage.addView(pieceView);
    }

    public void addImage(Uri uri) {
        DraweePieceView pieceView = new DraweePieceView(this);
        pieceView.setImageURI(uri);
        mPvImage.addView(pieceView);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
