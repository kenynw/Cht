package com.damenghai.chahuitong.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sgun on 15/8/13.
 */
public class Goods implements Serializable {
    /**
     * 商品id(SKU)
     */
    private String goods_id;

    /**
     * 商品名称（+规格名称）
     */
    private String goods_name;

    private String goods_num;

    private double goods_sum;

    private String goods_price;

    private String goods_marketprice;

    private String goods_image;

    private String goods_image_url;

    private String image_url;

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

    private String groupbuy_info;

    private String cart_id;

    private String buyer_id;

    private String have_gift;

    private String storage_state;

    private String fav;

    private String fav_id;

    private List<Attribute> goods_attr;

    private String title;

    private String remark;

    private String promotion_price;

    private String down_price;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setName(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGc_id() {
        return gc_id;
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }


    public double getGoods_sum() {
        return goods_sum;
    }

    public void setGoods_sum(double goods_sum) {
        this.goods_sum = goods_sum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getIs_fcode() {
        return is_fcode;
    }

    public void setIs_fcode(String is_fcode) {
        this.is_fcode = is_fcode;
    }

    public String getGoods_storage() {
        return goods_storage;
    }

    public void setGoods_storage(String goods_storage) {
        this.goods_storage = goods_storage;
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

    public String getGoods_freight() {
        return goods_freight;
    }

    public void setGoods_freight(String goods_freight) {
        this.goods_freight = goods_freight;
    }

    public String getGroupbuy_info() {
        return groupbuy_info;
    }

    public void setGroupbuy_info(String groupbuy_info) {
        this.groupbuy_info = groupbuy_info;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
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

    public String getHave_gift() {
        return have_gift;
    }

    public void setHave_gift(String have_gift) {
        this.have_gift = have_gift;
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

    public String getGoods_commonid() {
        return goods_commonid;
    }

    public void setGoods_commonid(String goods_commonid) {
        this.goods_commonid = goods_commonid;
    }

    public String getStorage_state() {
        return storage_state;
    }

    public void setStorage_state(String storage_state) {
        this.storage_state = storage_state;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getFav_id() {
        return fav_id;
    }

    public void setFav_id(String fav_id) {
        this.fav_id = fav_id;
    }

    public List<Attribute> getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(List<Attribute> goods_attr) {
        this.goods_attr = goods_attr;
    }

    public String getDown_price() {
        return down_price;
    }

    public void setDown_price(String down_price) {
        this.down_price = down_price;
    }

    public String getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(String promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
