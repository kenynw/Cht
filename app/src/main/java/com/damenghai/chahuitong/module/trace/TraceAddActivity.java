package com.damenghai.chahuitong.module.trace;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(TraceAddPresenter.class)
public class TraceAddActivity extends BeamBaseActivity<TraceAddPresenter> {

    @Bind(R.id.et_trace_content)
    EditText mEtContent;

    @Bind(R.id.pv_trace_image)
    PieceViewGroup mPvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trace_activity_add);
        setToolbarTitle(R.string.title_activity_trace_add);
        ButterKnife.bind(this);

        mPvImage.setOnViewDeleteListener(getPresenter());
        mPvImage.setOnAskViewListener(this::showSelectorDialog);
    }

    public void showSelectorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择图片来源")
                .setItems(new String[]{"拍照", "相册"}, (dialog, which) -> {
                    getPresenter().editFace(which);
                }).show();
    }

    public void addImage(Bitmap bitmap) {
        ImagePieceView pieceView = new ImagePieceView(this);
        pieceView.setImageBitmap(bitmap);
        mPvImage.addView(pieceView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().save(mEtContent.getText().toString().trim());
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
