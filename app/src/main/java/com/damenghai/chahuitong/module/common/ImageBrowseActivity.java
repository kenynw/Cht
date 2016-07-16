package com.damenghai.chahuitong.module.common;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Image;
import com.damenghai.chahuitong.widget.DraweePhotoView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageBrowseActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.pager_image_browse)
    ViewPager mViewPager;

    @Bind(R.id.tv_image_browse_indicator)
    TextView mTvIndicator;
    
    @Bind(R.id.iv_image_browse_back)
    ImageView mIvBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new ImagePagerAdapter(getIntent().getParcelableArrayListExtra("images")));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
        mIvBack.setOnClickListener(v -> finish());
        setIndicator(getIntent().getIntExtra("position", 0) + 1);
    }

    private void setIndicator(int position) {
        mTvIndicator.setText(String.format(getString(R.string.text_image_indicator), position, mViewPager.getAdapter().getCount()));
    }

    @Override
    public void onPageSelected(int position) {
        setIndicator(position + 1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    class ImagePagerAdapter extends PagerAdapter {

        private List<Image> mImageList;

        public ImagePagerAdapter(List<Image> imageList) {
            mImageList = imageList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ImageBrowseActivity.this).inflate(R.layout.item_pager_image, container, false);
            DraweePhotoView draweePhotoView = (DraweePhotoView) view.findViewById(R.id.photo_view_image_browse);
            draweePhotoView.setImageUri(mImageList.get(position).getThumb_mid());
            draweePhotoView.setOnPhotoTapListener((view1, x, y) -> finish());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mImageList == null ? 0 :mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
