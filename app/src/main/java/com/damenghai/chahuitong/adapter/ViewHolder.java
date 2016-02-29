package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * 用于ListView复用视图控件的辅助类
 * @author LiaoPeiKun
 *
 */
public class ViewHolder {
	/**
	 * 上下文
	 */
	private Context mContext;
	
	/**
	 * 用于存放视图中需要复用的控件对象，map integers to Objects
	 */
	private SparseArray<View> mViews;
	
	/**
	 * 存放当前Item的位置
	 */
	private int mPosition;
	
	/**
	 * 复用的布局文件
	 */
	private View mConvertView;

	/**
	 * 父布局
	 *
	 */
	private ViewGroup mParent;

    private ImageLoader mImageLoader;

	public ViewHolder(Context context, int resId, ViewGroup parent, int position) {
		mPosition = position;
		mContext = context;
		mParent = parent;
		mConvertView = LayoutInflater.from(context).inflate(resId, parent, false);
		mViews = new SparseArray<View>();
		mConvertView.setTag(this);
        mImageLoader = ImageLoader.getInstance();
	}
	
	/**
	 * 复用的入口函数，用于判断当前的视图是否有值，有值就不再重复New对象
	 * @param convertView 复用的视图
	 * @param context 创建视图的上下文
	 * @param resId 布局文件
	 * @param parent 
	 * @param position ListView中的Item位置
	 * @return
	 */
	public static ViewHolder get(View convertView, Context context, int resId, ViewGroup parent, int position) {
		if(convertView == null) {
			return new ViewHolder(context, resId, parent, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	
	/**
	 * 通过id获取存放在SparseArray<View>中View对象，如果没有这个对象就获取后put进去
	 * @param resId 需要查找的视图id
	 * @return 返回视图对象
	 */
	public<T extends View> T getView(int resId) {
		View view = mViews.get(resId);
		
		if(view == null) {
			view = mConvertView.findViewById(resId);
			mViews.put(resId, view);
		}
		return (T) view;
	}
	
	/**
	 * 获得复用视图
	 * @return
	 */
	public View getConvertView() {
		return mConvertView;
	}

	public ViewGroup getParent() {
		return mParent;
	}

	public int getPosition() {
		return mPosition;
	}

	public ViewHolder setVisibility(int viewId, int visibility) {
		getView(viewId).setVisibility(visibility);
		return this;
	}

	/**
	 * 设置TextView的文本
	 * @param viewId 需要设置的TextView控件
	 * @param text 需要设置的文本
	 * @return 返回Holder对象可用于链式编程
	 */
	public ViewHolder setText(int viewId, CharSequence text) {
		TextView tv = getView(viewId);
		if(tv != null) tv.setText(text);
		return this;
	}

	/**
	 * 设置TextView的文本
	 * @param viewId 需要设置的TextView控件
	 * @param resId 需要设置的文本
	 * @return 返回Holder对象可用于链式编程
	 */
	public ViewHolder setText(int viewId, int resId) {
		TextView tv = getView(viewId);
		if(tv != null) tv.setText(resId);
		return this;
	}

	/**
	 * 设置ImageView的背景资源
	 * @param viewId 需要设置的ImageView控件
	 * @param resId 需要设置的背景资源id
	 * @return 返回Holder对象可用于链式编程
	 */
	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView iv = getView(viewId);
		iv.setImageResource(resId);
		return this;
	}

    public ViewHolder displayImage(int resId, String url) {
        ImageView imageView = getView(resId);
        mImageLoader.displayImage(url, imageView);
        return this;
    }

    /**
     * 设置控件点击事件
     * @param resId
     *                  控件ID
     * @param l
     *                  监听事件
     * @return
     *                  返回Holder对象可用于链式编程
     */
    public ViewHolder setOnClickListener(int resId, View.OnClickListener l) {
        View view = getView(resId);
        if(view != null) view.setOnClickListener(l);
        return this;
    }

}
