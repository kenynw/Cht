package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgun on 15/8/13.
 */
public class Goods implements Parcelable {
    private int goods_id;

    private String goods_name;

    private int goods_num;

    private double goods_sum;

    private String goods_price;

    private String goods_marketprice;

    private String goods_promotion_price;

    private String goods_image;

    private String goods_image_url;

    private String goods_url;

    private String goods_vat;

    private String goods_total;

    private String goods_freight;

    private String goods_storage;

    private String goods_commonid;

    private String goods_storage_alarm;

    private String goods_salenum;

    private String goods_commentnum;

    private String gc_id;

    private String state;

    private String store_id;

    private String store_name;

    private String is_fcode;

    private String transport_id;

    private String bl_id;

    private Groupbuy groupbuy_info;

    private int cart_id;

    private String buyer_id;

    private String have_gift;

    private String storage_state;

    private String fav;

    private int fav_id;

    private boolean is_favorite;

    private List<Attribute> goods_attr;

    private String title;

    private String remark;

    private String promotion_price;

    private String down_price;

    /**
     * 是否抢购
     */
    private boolean group_flag;

    /**
     * 是否限时折扣
     */
    private boolean xianshi_flag;

    /**
     * 产地
     */
    private String origin;

    private String recommend_score;

    private String recommend_taste;

    private String recommend_light;

    private String recommend_aroma;

    private String recommend_leaf;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.goods_id);
        dest.writeString(this.goods_name);
        dest.writeInt(this.goods_num);
        dest.writeDouble(this.goods_sum);
        dest.writeString(this.goods_price);
        dest.writeString(this.goods_marketprice);
        dest.writeString(this.goods_promotion_price);
        dest.writeString(this.goods_image);
        dest.writeString(this.goods_image_url);
        dest.writeString(this.goods_url);
        dest.writeString(this.goods_vat);
        dest.writeString(this.goods_total);
        dest.writeString(this.goods_freight);
        dest.writeString(this.goods_storage);
        dest.writeString(this.goods_commonid);
        dest.writeString(this.goods_storage_alarm);
        dest.writeString(this.goods_salenum);
        dest.writeString(this.goods_commentnum);
        dest.writeString(this.gc_id);
        dest.writeString(this.state);
        dest.writeString(this.store_id);
        dest.writeString(this.store_name);
        dest.writeString(this.is_fcode);
        dest.writeString(this.transport_id);
        dest.writeString(this.bl_id);
        dest.writeSerializable(this.groupbuy_info);
        dest.writeInt(this.cart_id);
        dest.writeString(this.buyer_id);
        dest.writeString(this.have_gift);
        dest.writeString(this.storage_state);
        dest.writeString(this.fav);
        dest.writeInt(this.fav_id);
        dest.writeByte(this.is_favorite ? (byte) 1 : (byte) 0);
        dest.writeList(this.goods_attr);
        dest.writeString(this.title);
        dest.writeString(this.remark);
        dest.writeString(this.promotion_price);
        dest.writeString(this.down_price);
        dest.writeByte(this.group_flag ? (byte) 1 : (byte) 0);
        dest.writeByte(this.xianshi_flag ? (byte) 1 : (byte) 0);
        dest.writeString(this.origin);
        dest.writeString(this.recommend_score);
        dest.writeString(this.recommend_taste);
        dest.writeString(this.recommend_light);
        dest.writeString(this.recommend_aroma);
        dest.writeString(this.recommend_leaf);
    }

    public Goods() {
    }

    protected Goods(Parcel in) {
        this.goods_id = in.readInt();
        this.goods_name = in.readString();
        this.goods_num = in.readInt();
        this.goods_sum = in.readDouble();
        this.goods_price = in.readString();
        this.goods_marketprice = in.readString();
        this.goods_promotion_price = in.readString();
        this.goods_image = in.readString();
        this.goods_image_url = in.readString();
        this.goods_url = in.readString();
        this.goods_vat = in.readString();
        this.goods_total = in.readString();
        this.goods_freight = in.readString();
        this.goods_storage = in.readString();
        this.goods_commonid = in.readString();
        this.goods_storage_alarm = in.readString();
        this.goods_salenum = in.readString();
        this.goods_commentnum = in.readString();
        this.gc_id = in.readString();
        this.state = in.readString();
        this.store_id = in.readString();
        this.store_name = in.readString();
        this.is_fcode = in.readString();
        this.transport_id = in.readString();
        this.bl_id = in.readString();
        this.groupbuy_info = (Groupbuy) in.readSerializable();
        this.cart_id = in.readInt();
        this.buyer_id = in.readString();
        this.have_gift = in.readString();
        this.storage_state = in.readString();
        this.fav = in.readString();
        this.fav_id = in.readInt();
        this.is_favorite = in.readByte() != 0;
        this.goods_attr = new ArrayList<Attribute>();
        in.readList(this.goods_attr, Attribute.class.getClassLoader());
        this.title = in.readString();
        this.remark = in.readString();
        this.promotion_price = in.readString();
        this.down_price = in.readString();
        this.group_flag = in.readByte() != 0;
        this.xianshi_flag = in.readByte() != 0;
        this.origin = in.readString();
        this.recommend_score = in.readString();
        this.recommend_taste = in.readString();
        this.recommend_light = in.readString();
        this.recommend_aroma = in.readString();
        this.recommend_leaf = in.readString();
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel source) {
            return new Goods(source);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public double getGoods_sum() {
        return goods_sum;
    }

    public void setGoods_sum(double goods_sum) {
        this.goods_sum = goods_sum;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_marketprice() {
        return goods_marketprice;
    }

    public void setGoods_marketprice(String goods_marketprice) {
        this.goods_marketprice = goods_marketprice;
    }

    public String getGoods_promotion_price() {
        return goods_promotion_price;
    }

    public void setGoods_promotion_price(String goods_promotion_price) {
        this.goods_promotion_price = goods_promotion_price;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getGoods_vat() {
        return goods_vat;
    }

    public void setGoods_vat(String goods_vat) {
        this.goods_vat = goods_vat;
    }

    public String getGoods_total() {
        return goods_total;
    }

    public void setGoods_total(String goods_total) {
        this.goods_total = goods_total;
    }

    public String getGoods_freight() {
        return goods_freight;
    }

    public void setGoods_freight(String goods_freight) {
        this.goods_freight = goods_freight;
    }

    public String getGoods_storage() {
        return goods_storage;
    }

    public void setGoods_storage(String goods_storage) {
        this.goods_storage = goods_storage;
    }

    public String getGoods_commonid() {
        return goods_commonid;
    }

    public void setGoods_commonid(String goods_commonid) {
        this.goods_commonid = goods_commonid;
    }

    public String getGoods_storage_alarm() {
        return goods_storage_alarm;
    }

    public void setGoods_storage_alarm(String goods_storage_alarm) {
        this.goods_storage_alarm = goods_storage_alarm;
    }

    public String getGoods_salenum() {
        return goods_salenum;
    }

    public void setGoods_salenum(String goods_salenum) {
        this.goods_salenum = goods_salenum;
    }

    public String getGoods_commentnum() {
        return goods_commentnum;
    }

    public void setGoods_commentnum(String goods_commentnum) {
        this.goods_commentnum = goods_commentnum;
    }

    public String getGc_id() {
        return gc_id;
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getIs_fcode() {
        return is_fcode;
    }

    public void setIs_fcode(String is_fcode) {
        this.is_fcode = is_fcode;
    }

    public String getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(String transport_id) {
        this.transport_id = transport_id;
    }

    public String getBl_id() {
        return bl_id;
    }

    public void setBl_id(String bl_id) {
        this.bl_id = bl_id;
    }

    public Groupbuy getGroupbuy_info() {
        return groupbuy_info;
    }

    public void setGroupbuy_info(Groupbuy groupbuy_info) {
        this.groupbuy_info = groupbuy_info;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getHave_gift() {
        return have_gift;
    }

    public void setHave_gift(String have_gift) {
        this.have_gift = have_gift;
    }

    public String getStorage_state() {
        return storage_state;
    }

    public void setStorage_state(String storage_state) {
        this.storage_state = storage_state;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public int getFav_id() {
        return fav_id;
    }

    public void setFav_id(int fav_id) {
        this.fav_id = fav_id;
    }

    public boolean is_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public List<Attribute> getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(List<Attribute> goods_attr) {
        this.goods_attr = goods_attr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(String promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getDown_price() {
        return down_price;
    }

    public void setDown_price(String down_price) {
        this.down_price = down_price;
    }

    public boolean isGroup_flag() {
        return group_flag;
    }

    public void setGroup_flag(boolean group_flag) {
        this.group_flag = group_flag;
    }

    public boolean isXianshi_flag() {
        return xianshi_flag;
    }

    public void setXianshi_flag(boolean xianshi_flag) {
        this.xianshi_flag = xianshi_flag;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRecommend_score() {
        return recommend_score;
    }

    public void setRecommend_score(String recommend_score) {
        this.recommend_score = recommend_score;
    }

    public String getRecommend_taste() {
        return recommend_taste;
    }

    public void setRecommend_taste(String recommend_taste) {
        this.recommend_taste = recommend_taste;
    }

    public String getRecommend_light() {
        return recommend_light;
    }

    public void setRecommend_light(String recommend_light) {
        this.recommend_light = recommend_light;
    }

    public String getRecommend_aroma() {
        return recommend_aroma;
    }

    public void setRecommend_aroma(String recommend_aroma) {
        this.recommend_aroma = recommend_aroma;
    }

    public String getRecommend_leaf() {
        return recommend_leaf;
    }

    public void setRecommend_leaf(String recommend_leaf) {
        this.recommend_leaf = recommend_leaf;
    }

}
