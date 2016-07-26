package com.damenghai.chahuitong.module.flea;

import android.net.Uri;
import android.os.Bundle;

import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.utils.DraweePieceView;

public class FleaEditActivity extends FleaAddActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setData(Flea flea) {
        mEtTitle.setText(flea.getGoods_name());
        mEtDetail.setText(flea.getGoods_body());
        mBtnCate.setText(flea.getGc_name());
        mEtPrice.setText(flea.getGoods_store_price());
        mEtTag.setText(flea.getGoods_tag());
        mEtPhone.setText(flea.getFlea_pphone());
        mEtContact.setText(flea.getFlea_pname());
        mBtnLocation.setText(flea.getFlea_area_name());

        if (flea.getDesc_image() != null && flea.getDesc_image().size() > 0) {
            for (int i=0; i<flea.getDesc_image().size(); i++) {
                FleaImage fleaImage = flea.getDesc_image().get(i);
                addImage(Uri.parse(fleaImage.getThumb_small()));
            }
        }
    }

    public void addImage(Uri uri) {
        DraweePieceView pieceView = new DraweePieceView(this);
        pieceView.setImageURI(uri);
        mPvImage.addView(pieceView);
    }

}
