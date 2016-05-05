package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.module.goods.GoodsDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class SampleListAdapter extends RecyclerView.Adapter<SampleListAdapter.SampleViewHolder> {

    private Context mContext;

    private final List<Sample> mData;

    public SampleListAdapter(Context context, List<Sample> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_sample, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SampleViewHolder holder, int position) {
        final Sample sample = holder.mItem = mData.get(position);

        holder.mDvImage.setImageURI(Uri.parse(sample.getSampleImages().get(0).getImage_mid()));
        holder.mTvTitle.setText(sample.getSample_name());
        holder.mTvPrice.setText("￥" + sample.getSample_goods_price());
        holder.mTvLocation.setText(sample.getSample_origin_place());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", sample.getSample_link());
                mContext.startActivity(intent);
            }
        });
        holder.mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_id", sample.getSample_link());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.dv_sample_image)
        SimpleDraweeView mDvImage;

        @Bind(R.id.tv_sample_title)
        TextView mTvTitle;

        @Bind(R.id.tv_sample_price)
        TextView mTvPrice;

        @Bind(R.id.tv_sample_location)
        TextView mTvLocation;

        @Bind(R.id.btn_sample_buy)
        Button mBtnBuy;

        @Bind(R.id.btn_sample_comment)
        Button mBtnComment;

        public Sample mItem;

        public SampleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvTitle.getText() + "'";
        }
    }
}
