package com.damenghai.chahuitong.module.flea;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.utils.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FleaAddPresenter.class)
public class FleaAddActivity extends BaseDataActivity<FleaAddPresenter, Flea> {

    @Bind(R.id.tv_flea_title)
    TextView mTvTitle;

    @Bind(R.id.et_flea_title)
    EditText mEtTitle;

    @Bind(R.id.et_flea_detail)
    EditText mEtDetail;

    @Bind(R.id.rv_flea_image)
    RecyclerView mRvImage;

    @Bind(R.id.tv_flea_cate)
    TextView mTvCate;

    @Bind(R.id.et_flea_cate)
    EditText mEtCate;

    @Bind(R.id.tv_flea_price)
    TextView mTvPrice;

    @Bind(R.id.et_flea_price)
    EditText mEtPrice;

    @Bind(R.id.et_flea_unit)
    EditText mEtUnit;

    @Bind(R.id.check_flea_no_price)
    CheckBox mCbNoPrice;

    @Bind(R.id.tv_flea_tag)
    TextView mTvTag;

    @Bind(R.id.et_flea_tag)
    EditText mEtTag;

    @Bind(R.id.tv_flea_phone)
    TextView mTvPhone;

    @Bind(R.id.et_flea_phone)
    EditText mEtPhone;

    @Bind(R.id.tv_flea_contact)
    TextView mTvContact;

    @Bind(R.id.et_flea_contact)
    EditText mEtContact;

    @Bind(R.id.tv_flea_location)
    TextView mTvLocation;

    @Bind(R.id.btn_flea_location)
    Button mBtnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flea_activity_add);
        setToolbarTitle(R.string.title_publish);
        ButterKnife.bind(this);

        mBtnLocation.setOnClickListener(v -> getPresenter().uploadImage("/storage/sdcard/Download/0b4b29aa18c2715c30f1707eab00e76f.jpeg"));
    }

    @Override
    public void setData(Flea flea) {
        mEtTitle.setText(flea.getGoods_name());
        mEtDetail.setText(flea.getGoods_body());
        mEtPrice.setText(flea.getGoods_store_price());
        mEtTag.setText(flea.getGoods_tag());
        mEtPhone.setText(flea.getFlea_pphone());
        mEtContact.setText(flea.getFlea_pname());
        mBtnLocation.setText(flea.getFlea_area_name());
    }



}
