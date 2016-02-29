package com.damenghai.chahuitong.utils;

import android.graphics.Bitmap;

import com.damenghai.chahuitong.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageOptHelper {
	
	public static DisplayImageOptions getImgOptions() {
		DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
			.cacheOnDisk(true)
			.cacheInMemory(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageOnLoading(R.mipmap.def_image_loading)
			.showImageForEmptyUri(R.mipmap.def_image_loading)
			.showImageOnFail(R.mipmap.def_image_failure)
			.build();
		return imgOptions;
	}

	public static DisplayImageOptions getAvatarOptions() {
		DisplayImageOptions	avatarOptions = new DisplayImageOptions.Builder()
			.cacheOnDisk(true)
			.cacheInMemory(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageOnLoading(R.mipmap.def_avatar_placeholder)
			.showImageForEmptyUri(R.mipmap.def_avatar_placeholder)
			.showImageOnFail(R.mipmap.def_avatar_placeholder)
			.displayer(new RoundedBitmapDisplayer(999))
			.build();
		return avatarOptions;
	}

	public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheOnDisk(true)
			.cacheInMemory(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageOnLoading(R.mipmap.def_image_loading)
			.showImageForEmptyUri(R.mipmap.def_image_loading)
			.showImageOnFail(R.mipmap.def_image_loading)
			.displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
		return options;
	}
}
