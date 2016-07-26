package com.damenghai.chahuitong.module.flea;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.ImagePagerAdapter;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.widget.HeadViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FleaDetailPresenter.class)
public class FleaDetailActivity extends BaseDataActivity<FleaDetailPresenter, Flea> {

    @Bind(R.id.pager_flea_images)
    HeadViewPager mPagerImage;

    @Bind(R.id.tv_flea_price)
    TextView mTvPrice;

    @Bind(R.id.tv_flea_time)
    TextView mTvTime;

    @Bind(R.id.tv_flea_title)
    TextView mTvTitle;

    @Bind(R.id.tv_flea_area)
    TextView mTvArea;

    @Bind(R.id.tv_flea_click)
    TextView mTvClick;

    @Bind(R.id.tv_flea_tag)
    TextView mTvTag;

    @Bind(R.id.ll_flea_user)
    LinearLayout mLayoutUser;

    @Bind(R.id.dv_flea_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_flea_username)
    TextView mTvUsername;

    @Bind(R.id.tv_flea_detail)
    TextView mTvDetail;

    @Bind(R.id.rcv_flea_consult)
    EasyRecyclerView mRvConsult;

    @Bind(R.id.layout_flea_contact)
    LinearLayout mLayoutContact;

    @Bind(R.id.tv_flea_p_phone)
    TextView mTvPhone;

    @Bind(R.id.tv_flea_p_name)
    TextView mTvName;

    @Bind(R.id.btn_flea_favorites)
    CheckBox mCbFav;

    @Bind(R.id.btn_flea_consult)
    Button mBtnConsult;

    @Bind(R.id.btn_flea_share)
    Button mBtnChat;

    @Bind(R.id.layout_flea_publish)
    LinearLayout mLayoutPublish;

    @Bind(R.id.iv_flea_back)
    ImageView mIvBack;

    @Bind(R.id.et_flea_consult)
    EditText mEtConsult;

    @Bind(R.id.btn_flea_publish)
    Button mBtnPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flea_activity_detail);
        setToolbarTitle(R.string.title_activity_detail);
        ButterKnife.bind(this);

        mRvConsult.setLayoutManager(new LinearLayoutManager(this));
        mRvConsult.setEmptyView(R.layout.empty_list_comment);

        mBtnConsult.setOnClickListener(v -> showPublish());
        mIvBack.setOnClickListener(v -> showContact());
        mBtnPublish.setOnClickListener(v -> getPresenter().publishConsult(mEtConsult.getText().toString()));
        mEtConsult.addTextChangedListener(getPresenter());
    }

    @Override
    public void setData(Flea flea) {
        ArrayList<Image> list = new ArrayList<>();
        if (flea.getDesc_image() != null && flea.getDesc_image().size() > 0) {
            for (FleaImage fleaImage : flea.getDesc_image()) {
                Image image = new Image();
                image.setThumb_max(fleaImage.getThumb_max());
                image.setThumb_mid(fleaImage.getThumb_mid());
                image.setThumb_small(fleaImage.getThumb_small());
                list.add(image);
            }
        }
        mPagerImage.setAdapter(new ImagePagerAdapter(this, list));
        mRvConsult.setAdapter(getPresenter().getAdapter(flea.getConsult_list()));

        mTvTitle.setText(flea.getGoods_name());
        mTvTime.setText(flea.getGoods_add_time());
        mTvPrice.setText(flea.getGoods_store_price());
        mTvArea.setText(flea.getFlea_area_name());
        mTvClick.setText(String.format(getString(R.string.text_flea_click), flea.getGoods_click()));
        mTvTag.setText(flea.getGoods_tag().trim());
        mDvAvatar.setImageURI(Uri.parse(flea.getMember_avatar()));
        mTvUsername.setText(flea.getMember_name());
        mLayoutUser.setOnClickListener(v -> getPresenter().showUser(flea.getMember_id()));
        mTvDetail.setText(flea.getGoods_body());
        mTvPhone.setText(flea.getFlea_pphone());
        mTvName.setText(flea.getFlea_pname());
        mCbFav.setChecked(flea.isFavorite());
        mCbFav.setOnCheckedChangeListener(getPresenter());
    }

    private void showPublish() {
        mLayoutContact.setVisibility(View.GONE);
        mLayoutPublish.setVisibility(View.VISIBLE);
    }

    private void showContact() {
        mLayoutContact.setVisibility(View.VISIBLE);
        mLayoutPublish.setVisibility(View.GONE);
    }

    public EditText getEtConsult() {
        return mEtConsult;
    }

    public Button getBtnPublish() {
        return mBtnPublish;
    }

}
