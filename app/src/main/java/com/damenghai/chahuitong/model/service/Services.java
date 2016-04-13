package com.damenghai.chahuitong.model.service;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.model.bean.Token;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.bean.Valuator;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.OrderList;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface Services {

    String BASE_URL = "http://www.chahuitong.com";

    @FormUrlEncoded
    @POST("/mobile/index.php?act=home&op=home_page_api")
    Observable<Home> home(
            @Field("key") String key,
            @Field("page") int page
    );

    //--------------------------专题--------------------------
    // 免费茶样往期列表
    @GET("/mobile/index.php?act=goods_sample&op=overdue_goods")
    Observable<Response<List<Sample>>> sampleList();

    // 免费茶样当前
    @FormUrlEncoded
    @POST("/mobile/index.php?act=goods_sample")
    Observable<Sample> curSample(
            @Field("key") String key
    );

    // 限时抢购
    @FormUrlEncoded
    @POST("/mobile/index.php?act=flash_sale")
    Observable<Response<List<Bargain>>> bargainList(
            @Field("page") int page,
            @Query("op") String op
    );

    // 茶艺师推荐
    @FormUrlEncoded
    @POST("/mobile/index.php?act=goods&op=recommend_list")
    Observable<Response<Valuator>> recommendList(
            @Field("page") int page,
            @Field("gc_id") String byCate, // 按分类进行筛选，类目ID
            @Field("byPrice") int byPrice, // 按 价格进行筛选，值为1 倒序 值为 0 顺序
            @Field("bySalenum") int bySales, // 按销量进行筛选，值为1 倒序 值为 0 顺序
            @Field("byClick") int bySeason  // 按点击量进行筛选，值为1 倒序 值为 0 顺序
    );

    //--------------------------商品--------------------------

    /**
     * @param key 排序方式 1-销量 2-浏览量 3-价格 空-按最新发布排序
     * @param order 排序方式 1-升序 2-降序
//     * @param page 每页数量
     * @param curPage 当前页码
     * @param gc_id 分类编号
     * @param keyword 搜索关键字 注：gc_id和keyword二选一不能同时出现
     * @return
     */
    @GET("/mobile/index.php?act=goods")
    Observable<BeanList<Goods>> goodsList(
            @Query("op") String op,
            @Query("version") String version,
            @Query("key") String key,
            @Query("order") int order,
//            @Query("page") int page,
            @Query("curpage") int curPage,
            @Query("gc_id") int gc_id,
            @Query("keyword") CharSequence keyword
    );

    @GET("/mobile/index.php?act=goods&op=goods_detail_api")
    Observable<GoodsInfo> goodsDetail(
            @Query("goods_id") String goods_id,
            @Query("key") String key
    );

    @GET("/mobile/index.php?act=goods&op=goods_body")
    Observable<GoodsInfo> goodsBody(
            @Query("goods_id") String goods_id
    );

    @GET("/mobile/index.php?act=goods_class")
    Observable<List<Category>> goodsClass(
            @Query("version") String version,
            @Query("gc_id") int gc_id
    );

    //--------------------------登录注册--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=login")
    Observable<Response<Account>> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=register_api")
    Observable<JsonObject> thirdLogin(
            @Field("key") String key,
            @Field("client") String client,
            @Field("op") String type,
            @Field("openid") String openid
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=register_api")
    Observable<Response<Token>> register(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("verificode") String code,
            @Field("label") String label,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=send_sms")
    Observable<Response> sendCaptcha(
            @Field("mobile") CharSequence mobile
    );

    //--------------------------密码--------------------------
    /**
     * 忘记密码
     * @param mobile 用户手机号
     * @param code  验证码
     * @param newPwd    新密码
     * @return
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=change_pwd")
    Observable<Response> resetPassword(
            @Field("mobile") CharSequence mobile,
            @Field("verificode") CharSequence code,
            @Field("newpwd") CharSequence newPwd
    );

    // 更改密码
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_index&op=update_member_pwd")
    Observable<Response> changePassword(
            @Field("key") String key,
            @Field("oldpwd") CharSequence oldPwd,
            @Field("newpwd") CharSequence newPwd
    );

    //--------------------------用户信息--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_index")
    Observable<User> getUser(
            @Field("version") String version,
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_index&op=get_member_info")
    Observable<Response<User>> getUserInfo(
            @Field("key") String key
    );

    @Multipart
    @POST("/mobile/index.php?act=member_index&op=update_member_info")
    Observable<JsonObject> updateUserInfo(
            @PartMap Map<String, RequestBody> params
    );

    //--------------------------购买--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=buy_step1")
    Observable<OrderInfo> getOrderInfo(
            @Field("version") String version,
            @Field("key") String key,
            @Field("cart_id") String cartId,
            @Field("ifcart") String isCart
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=buy_step2")
    Observable<JsonObject> genOrder(
            @Field("key") String key,
            @Field("cart_id") String cart_id,
            @Field("address_id") String address_id,
            @Field("vat_hash") String vat_hash,
            @Field("freight_hash") String freight_hash,
            @Field("offpay_hash") String offpay_hash,
            @Field("offpay_hash_batch") String offpay_hash_batch,
            @Field("pay_name") String pay_name,
            @Field("ifcart") String ifcart,
            @Field("allow_offpay") String allow_offpay,
            @Field("voucher") String voucher
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=change_address")
    /**
     * @param key 当前登录令牌
     * @param freight_hash 运费hash，第一步返回结果里有直接提交
     * @param city_id 城市编号
     * @param area_id 地区编号
     */
    Observable<JsonObject> changeAddress(
            @Field("key") String key,
            @Field("freight_hash") String freight_hash,
            @Field("city_id") String city_id,
            @Field("area_id") String area_id
    );

    //--------------------------我的购物车--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_list")
    Observable<Response<Cart>> cartList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_add")
    Observable<String> cartAdd(
            @Field("key") String key,
            @Field("goods_id") String goods_id,
            @Field("quantity") String quantity
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_del")
    Observable<JsonObject> cartDel(
            @Field("key") String key,
            @Field("cart_id") String cart_id
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_edit_quantity")
    Observable<JsonObject> cartEdit(
            @Field("key") String key,
            @Field("cart_id") String cart_id,
            @Field("quantity") String quantity
    );

    //--------------------------我的收藏--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_list")
    Observable<Response<ListResponse>> favList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_add")
    Observable<String> favAdd(
            @Field("key") String key,
            @Field("goods_id") String goods_id,
            @Field("version") String version
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_del")
    Observable<Response<String>> favDel(
            @Field("key") String key,
            @Field("fav_id") String fav_id
    );

    //--------------------------我的地址--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_list")
    Observable<Response<ListResponse>> addressList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_add")
    Observable<JsonObject> addressAdd(
            @Field("key") String key,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") String cityId,
            @Field("area_id") String areaId,
            @Field("area_info") String area_info,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_del")
    Observable<JsonObject> addressDel(
            @Field("key") String key,
            @Field("address_id") String addressId
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_edit")
    Observable<JsonObject> addressEdit(
            @Field("key") String key,
            @Field("address_id") String addressId,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") String cityId,
            @Field("area_id") String areaId,
            @Field("area_info") String area_info,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=area_list")
    Observable<Response<ListResponse>> areaList(
            @Field("key") String key,
            @Field("area_id") String areaId
    );

    //--------------------------我的订单--------------------------
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_list")
    Observable<OrderList> orderList(
            @Field("version") String version,
            @Field("key") String key,
            @Field("order_state") String state, //订单状态。10-待支付 20-待发货 30-待收货 40待评价
            @Query("curpage") int curPage
    );

    /**
     * 取单条订单信息
     * @param key 登录信息
     * @param order_id 订单ID
     * @return
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_info")
    Observable<Order> orderInfo(
            @Field("key") String key,
            @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_cancel")
    Observable<JsonObject> orderCancel(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_receive")
    Observable<JsonObject> orderSure(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

    //--------------------------我的优惠券--------------------------
    @FormUrlEncoded
    @POST("/wap/index.php/Home/Index/get_voucher_api/")
    Observable<Response<List<Voucher>>> voucherList(
            @Field("key") String key,
            @Field("voucher_state") String state
    );

}
