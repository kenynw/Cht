package com.damenghai.chahuitong.model.service;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.Article;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Consult;
import com.damenghai.chahuitong.model.bean.Discover;
import com.damenghai.chahuitong.model.bean.Flea;
import com.damenghai.chahuitong.model.bean.FleaCate;
import com.damenghai.chahuitong.model.bean.FleaImage;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsComment;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.bean.Message;
import com.damenghai.chahuitong.model.bean.MessageCount;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.model.bean.People;
import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.model.bean.Token;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.damenghai.chahuitong.model.bean.UpdateInfo;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface Services {

    String BASE_URL = "http://api.chahuitong.com/";

    String BACK_URL = "http://120.25.216.71/api/";

    // 首页
    @FormUrlEncoded
    @POST("?act=home")
    Observable<Home> home(
            @Field("key") String key
    );

    //--------------------------专题--------------------------
    // 免费茶样往期列表
    @GET("?act=goods_sample&op=overdue_goods")
    Observable<Response<List<Sample>>> sampleList();

    // 免费茶样当前
    @FormUrlEncoded
    @POST("?act=goods_sample")
    Observable<Sample> curSample(
            @Field("key") String key
    );

    // 限时抢购
    @FormUrlEncoded
    @POST("?act=xianshi_goods&op=current_list")
    Observable<BeanList<Bargain>> bargainList(
            @Field("page") int page
    );

    //--------------------------商品--------------------------

    /**
     * @param op 接口名字 goods_list-所有商品列表 recommend_list-茶艺师推荐列表
     * @param key 排序方式 1-销量 2-浏览量 3-价格 空-按最新发布排序
     * @param order 排序方式 1-升序 2-降序
     * @param curPage 当前页码
     * @param gc_id 分类编号
     * @param keyword 搜索关键字 注：gc_id和keyword二选一不能同时出现
     * @return
     */
    @GET("?act=goods")
    Observable<BeanList<Goods>> goodsList(
            @Query("op") String op,
            @Query("version") String version,
            @Query("key") String key,
            @Query("order") int order,
            @Query("curpage") int curPage,
            @Query("gc_id") int gc_id,
            @Query("keyword") CharSequence keyword
    );

    @GET("?act=goods&op=goods_detail")
    Observable<GoodsInfo> goodsDetail(
            @Query("version") String version,
            @Query("goods_id") int goods_id,
            @Query("key") String key
    );

    @GET("?act=goods&op=goods_body")
    Observable<GoodsInfo> goodsBody(
            @Query("goods_id") String goods_id
    );

    @GET("?act=goods&op=comments_list")
    Observable<BeanList<GoodsComment>> goodsComments(
            @Query("goods_id") String goodsId
    );

    @GET("?act=goods_class")
    Observable<List<Category>> goodsClass(
            @Query("version") String version,
            @Query("gc_id") int gc_id
    );

    @GET("?act=goods&op=hot_search")
    Observable<String[]> hotSearch();

    //--------------------------登录注册--------------------------
    @FormUrlEncoded
    @POST("?act=login")
    Observable<User> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("?act=logout")
    Observable<String> logout(
            @Field("key") String key,
            @Field("mobile") String mobile,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("?act=login&op=register_api")
    Observable<JsonObject> thirdLogin(
            @Field("key") String key,
            @Field("client") String client,
            @Field("op") String type,
            @Field("openid") String openid
    );

    @FormUrlEncoded
    @POST("?act=login&op=register_api")
    Observable<User> register(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("verificode") String code,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("?act=login&op=send_sms")
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
    @POST("?act=login&op=change_pwd")
    Observable<Response> resetPassword(
            @Field("mobile") CharSequence mobile,
            @Field("verificode") CharSequence code,
            @Field("newpwd") CharSequence newPwd
    );

    // 更改密码
    @FormUrlEncoded
    @POST("?act=member_index&op=update_member_pwd")
    Observable<Response> changePassword(
            @Field("key") String key,
            @Field("oldpwd") CharSequence oldPwd,
            @Field("newpwd") CharSequence newPwd
    );

    //--------------------------用户信息--------------------------
    @FormUrlEncoded
    @POST("?act=member_index")
    Observable<User> getUser(
            @Field("version") String version,
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("?act=member_index&op=member_info")
    Observable<User> profile(
            @Field("key") String key
    );

    @Multipart
    @POST("?act=member_index&op=upload_avatar")
    Observable<Boolean> uploadAvatar(
            @PartMap Map<String, RequestBody> params
    );

    @POST("?act=member_index&op=update_member_info")
    Observable<Boolean> updateUserInfo(
            @Body User user,
            @Query("key") String key
    );

    //--------------------------购买--------------------------
    @FormUrlEncoded
    @POST("?act=member_buy&op=buy_step1")
    Observable<OrderInfo> getOrderInfo(
            @Field("version") String version,
            @Field("key") String key,
            @Field("cart_id") String cartId,
            @Field("ifcart") String isCart
    );

    @FormUrlEncoded
    @POST("?act=member_buy&op=buy_step2")
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

    /**
     * @param key 当前登录令牌
     * @param freight_hash 运费hash，第一步返回结果里有直接提交
     * @param city_id 城市编号
     * @param area_id 地区编号
     */
    @FormUrlEncoded
    @POST("?act=member_buy&op=change_address")
    Observable<JsonObject> changeAddress(
            @Field("key") String key,
            @Field("freight_hash") String freight_hash,
            @Field("city_id") int city_id,
            @Field("area_id") int area_id
    );

    //--------------------------我的购物车--------------------------
    @FormUrlEncoded
    @POST("?act=member_cart&op=cart_list")
    Observable<Cart> cartList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("?act=member_cart&op=cart_add")
    Observable<String> cartAdd(
            @Field("key") String key,
            @Field("goods_id") int goods_id,
            @Field("quantity") int quantity
    );

    @FormUrlEncoded
    @POST("?act=member_cart&op=cart_del")
    Observable<Boolean> cartDel(
            @Field("key") String key,
            @Field("cart_id") String cart_id
    );

    @FormUrlEncoded
    @POST("?act=member_cart&op=cart_edit_quantity")
    Observable<Cart> cartEditNum(
            @Field("key") String key,
            @Field("cart_id") int cart_id,
            @Field("quantity") int quantity
    );

    @FormUrlEncoded
    @POST("?act=member_cart&op=move_favorites")
    Observable<Boolean> moveCartFav(
            @Field("key") String key,
            @Field("cart_id") String cartID
    );

    //--------------------------我的收藏--------------------------
    @FormUrlEncoded
    @POST("?act=member_favorites&op=favorites_list")
    Observable<BeanList<Goods>> favList(
            @Field("version") String version,
            @Field("key") String key,
            @Query("curpage") int curPage
    );

    @FormUrlEncoded
    @POST("?act=member_favorites&op=favorites_add")
    Observable<String> favAdd(
            @Field("key") String key,
            @Field("goods_id") int goodsID,
            @Field("version") String version
    );

    @FormUrlEncoded
    @POST("?act=member_favorites&op=favorites_del")
    Observable<String> favDel(
            @Field("version") String version,
            @Field("key") String key,
            @Field("fav_id") int favID
    );

    //--------------------------我的地址--------------------------
    @FormUrlEncoded
    @POST("?act=member_address&op=address_list")
    Observable<BeanList<Address>> addressList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("?act=member_address&op=address_add")
    Observable<Integer> addressAdd(
            @Field("key") String key,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") int cityId,
            @Field("area_id") int areaId,
            @Field("area_info") String area_info,
            @Field("address") String address,
            @Field("is_default") int isDefault
    );

    @FormUrlEncoded
    @POST("?act=member_address&op=address_del")
    Observable<Boolean> addressDel(
            @Field("key") String key,
            @Field("address_id") int addressId
    );

    @FormUrlEncoded
    @POST("?act=member_address&op=address_edit")
    Observable<Integer> addressEdit(
            @Field("key") String key,
            @Field("address_id") int addressId,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") int cityId,
            @Field("area_id") int areaId,
            @Field("area_info") String area_info,
            @Field("address") String address,
            @Field("is_default") int isDefault
    );

    @FormUrlEncoded
    @POST("?act=member_address&op=set_default")
    Observable<Boolean> addressSetDefault(
            @Field("key") String key,
            @Field("address_id") int id
    );

    @FormUrlEncoded
    @POST("?act=member_address&op=area_list")
    Observable<BeanList<Area>> areaList(
            @Field("key") String key,
            @Field("area_id") int areaId
    );

    //--------------------------我的订单--------------------------
    @FormUrlEncoded
    @POST("?act=member_order&op=order_list")
    Observable<BeanList<Order>> orderList(
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
    @POST("?act=member_order&op=order_info")
    Observable<Order> orderInfo(
            @Field("key") String key,
            @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("?act=member_order&op=order_cancel")
    Observable<String> orderCancel(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

    @FormUrlEncoded
    @POST("?act=member_order&op=order_receive")
    Observable<String> orderSure(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

    //--------------------------我的优惠券--------------------------
    /**
     * @param version 版本号
     * @param key 当前登录令牌
     * @param state 代金券状态(1-未使用 2-已使用 3-已过期)
     * @return
     */
    @FormUrlEncoded
    @POST("?act=member_voucher&op=voucher_list")
    Observable<BeanList<Voucher>> voucherList(
            @Field("version") String version,
            @Field("key") String key,
            @Field("voucher_state") int state
    );

    //--------------------------我的消息--------------------------

    @GET("?act=member_message&op=new_count")
    Observable<MessageCount> newMsgCount(
            @Query("key") String key
    );

    /**
     * @param key 登录令牌
     * @param type 消息类型 0为私信、1为系统消息、2为留言、3为新的粉丝、4为评论和赞、5为@我
     * @return 消息列表
     */
    @FormUrlEncoded
    @POST("?act=member_message&op=message_list")
    Observable<BeanList<Message>> msgList(
            @Field("key") String key,
            @Field("type") int type,
            @Query("curpage") int page
    );

    /**
     * 发现首页
     */
    @GET("?act=discover")
    Observable<Discover> discover();

    /**
     * 文章列表
     * @param key 登录令牌
     * @param curPage 当前页
     * @return 文章列表
     */
    @GET("?act=article&op=article_list")
    Observable<BeanList<Article>> articleList(
            @Query("key") String key,
            @Query("curpage") int curPage
    );

    /**
     * 文章详情
     * @param key 登录令牌
     * @param articleID 文章ID
     * @return 文章列表
     */
    @GET("?act=article&op=article_detail")
    Observable<Article> articleDetail(
            @Query("key") String key,
            @Query("article_id") int articleID
    );

    //////////////////////////茶友圈/////////////////////////

    /**
     * 用户关注列表
     * @param key 当前登录令牌
     * @param mid 主页用户ID
     * @param type 列表类型 0-关注列表 1-粉丝列表
     * @return 用户列表
     */
    @GET("?act=member_sns_friend&op=follow_list")
    Observable<BeanList<User>> followList(
            @Query("key") String key,
            @Query("curpage") int curPage,
            @Query("mid") int mid,
            @Query("type") int type
    );

    /**
     * 好友动态列表
     * @param key 登录令牌
     * @param curPage 当前页数
     * @return
     */
    @GET("?act=member_sns_trace&op=trace_list")
    Observable<BeanList<Trace>> friendTraceList(
            @Query("key") String key,
            @Query("curpage") int curPage
    );

    /**
     * 动态详情
     * @param key 登录令牌
     * @param traceId 当前页数
     * @return 动态详情
     */
    @GET("?act=member_sns_trace&op=trace_detail")
    Observable<Trace> traceDetail(
            @Query("key") String key,
            @Query("id") int traceId
    );

    /**
     * 动态上传图片
     */
    @Multipart
    @POST("?act=member_sns_trace&op=trace_image_upload")
    Call<Integer> traceUploadImage(
            @PartMap Map<String, RequestBody> params
    );

    /**
     * 删除动态
     * @param key 当前登录令牌
     * @param content 内容
     */
    @FormUrlEncoded
    @POST("?act=member_sns_trace&op=trace_add")
    Observable<Integer> traceAdd(
            @Field("key") String key,
            @Field("content") String content,
            @Field("image_id") int imageId
    );

    /**
     * 删除动态
     * @param key 当前登录令牌
     * @param traceId 动态id
     */
    @GET("?act=member_sns_trace&op=trace_del")
    Observable<String> traceDel(
            @Query("key") String key,
            @Query("id") int traceId
    );

    /**
     * 添加动态评论
     * @param key 当前登录令牌
     * @param traceId 动态id
     * @param content 评论内容
     */
    @FormUrlEncoded
    @POST("?act=member_sns_trace&op=comment_add")
    Observable<TraceComment> addTraceComment(
            @Field("key") String key,
            @Field("id") int traceId,
            @Field("content") String content
    );

    /**
     * 添加动态评论
     * @param key 当前登录令牌
     * @param commentId 评论ID
     * @param content 评论内容
     */
    @FormUrlEncoded
    @POST("?act=member_sns_trace&op=comment_reply")
    Observable<TraceComment> replyTraceComment(
            @Field("key") String key,
            @Field("id") int commentId,
            @Field("content") String content
    );

    /**
     * 删除动态评论
     * @param key 当前登录令牌
     * @param id 评论ID
     */
    @GET("?act=member_sns_trace&op=comment_del")
    Observable<String> delTraceComment(
            @Query("key") String key,
            @Query("id") int id
    );

    /**
     * 动态和评论点赞
     * @param key 当前登录令牌
     * @param id 动态或评论id
     * @param type 原帖类型 0表示动态 1表示分享商品 2表示评论 默认为0
     */
    @FormUrlEncoded
    @POST("?act=member_sns_trace&op=like_add")
    Observable<String> addLike(
            @Field("key") String key,
            @Field("id") int id,
            @Field("type") int type
    );

    /**
     * 用户主页
     * @param key 当前登录令牌
     * @param mid 主页用户ID
     * @return
     */
    @GET("?act=member_sns_home")
    Observable<People> userHome(
            @Query("key") String key,
            @Query("mid") int mid
    );

    /**
     * 全部动态列表
     * @param mid 指定某用户
     * @param isCommend 是否推荐 0-否 1-推荐
     * @param curPage 当前页
     * @return
     */
    @GET("?act=member_sns_home&op=trace_list")
    Observable<BeanList<Trace>> traceList(
            @Query("key") String key,
            @Query("mid") int mid,
            @Query("commend") int isCommend,
            @Query("curpage") int curPage
    );

    /**
     * @param key 登录令牌
     * @return 社区达人列表
     */
    @GET("?act=member_sns_friend&op=popular_list")
    Observable<BeanList<People>> popularList(
            @Query("key") String key,
            @Query("curpage") int page
    );

    /**
     * @param key 登录令牌
     * @return 社区达人列表
     */
    @GET("?act=member_sns_friend&op=find_list")
    Observable<BeanList<User>> findList(
            @Query("key") String key,
            @Query("name") String name,
            @Query("curpage") int page
    );

    /**
     * 添加关注
     * @param key 登录令牌
     * @param uid 用户ID
     * @return 成功ID
     */
    @GET("?act=member_sns_friend&op=add_follow")
    Observable<Integer> addFollow(
            @Query("key") String key,
            @Query("mid") int uid
    );

    /**
     * 添加关注
     * @param key 登录令牌
     * @param uid 用户ID
     * @return 成功ID
     */
    @GET("?act=member_sns_friend&op=del_follow")
    Observable<Integer> delFollow(
            @Query("key") String key,
            @Query("mid") int uid
    );

    /**
     *
     * @param key 登录信息
     * @param traceID 动态ID
     * @param replyID 评论ID
     * @param content 举报内容
     * @param type 类型 0-动态 1-评论
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_sns_inform&op=inform_trace")
    Observable<Boolean> informTrace(
            @Field("key") String key,
            @Field("trace_id") int traceID,
            @Field("reply_id") int replyID,
            @Field("inform_content") String content,
            @Field("type") int type
    );

    /**
     *
     * @param key 登录信息
     * @param mid 举报用户的ID
     * @param content 举报内容
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_sns_inform&op=inform_home")
    Observable<Boolean> informHome(
            @Field("key") String key,
            @Field("mid") int mid,
            @Field("inform_content") String content
    );

    /**
     * @param key 登录令牌
     * @return 我发布的茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea")
    Observable<BeanList<Flea>> myFleaList(
            @Field("key") String key
    );

    /**
     * @param key 登录令牌
     * @return 茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=flea_list")
    Observable<BeanList<Flea>> fleaList(
            @Field("key") String key,
            @Query("curpage") int page
    );

    /**
     * 茶市详情
     * @param key 登录令牌
     * @return 茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=flea_detail")
    Observable<Flea> fleaDetail(
            @Field("key") String key,
            @Field("goods_id") int goodsId
    );

    /**
     * 发布茶市上传图片
     * @return 茶市列表
     */
    @Multipart
    @POST("?act=member_flea&op=image_upload")
    Call<FleaImage> uploadFleaImage(
            @PartMap Map<String, RequestBody> params
    );

    /**
     * 发布茶市保存文字
     * @param key 登录令牌
     * @return 茶市ID
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=save_goods")
    Observable<Integer> saveFlea(
            @Field("key") String key,
            @Field("goods_name") String title,
            @Field("g_body") String body,
            @Field("goods_file_id") int image_ids,
            @Field("cate_id") int cateId,
            @Field("cate_name") String cateName,
            @Field("price") double price,
            @Field("goods_tag") String tag,
            @Field("flea_pname") String pName,
            @Field("flea_pphone") String pPhone,
            @Field("area_id") int areaId,
            @Field("area_info") String areaInfo
    );

    /**
     * 茶市收藏列表
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=favorites")
    Observable<BeanList<Flea>> fleaFavorites(
            @Field("key") String key,
            @Field("curpage") int curPage
    );

    /**
     * 茶市收藏列表
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=add_favorites")
    Observable<Boolean> fleaAddFavorite(
            @Field("key") String key,
            @Field("goods_id") int goodsId
    );

    /**
     * 茶市收藏列表
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=del_favorites")
    Observable<Boolean> fleaDelFavorite(
            @Field("key") String key,
            @Field("goods_id") int goodsId
    );

    /**
     * 茶市商品留言
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=class_list")
    Observable<List<FleaCate>> fleaCateList(
            @Field("key") String key,
            @Field("class_id") int cateId
    );

    /**
     * 茶市商品留言
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=consult_list")
    Observable<BeanList<Consult>> fleaConsultList(
            @Field("key") String key,
            @Field("curpage") int page,
            @Field("goods_id") int goods_id
    );

    /**
     * 茶市商品留言
     * @param key 登录令牌
     * @return 操作结果
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=save_consult")
    Observable<Consult> addFleaConsult(
            @Field("key") String key,
            @Field("content") String content,
            @Field("goods_id") int goods_id
    );

    /**
     * @param key 登录令牌
     * @return 茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=drop_goods")
    Observable<Boolean> fleaDel(
            @Field("key") String key,
            @Field("goods_id") int goods_id
    );

    /**
     * @param key 登录令牌
     * @return 茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=edit_goods")
    Observable<Boolean> fleaEdit(
            @Field("key") String key,
            @Field("goods_id") int goodsID,
            @Field("goods_name") String title,
            @Field("cate_id") int cateId,
            @Field("cate_name") String cateName,
            @Field("flea_pname") String name,
            @Field("area_id") int areaId,
            @Field("area_info") String areaInfo,
            @Field("flea_pphone") String Phone,
            @Field("goods_tag") String goodsTag,
            @Field("price") double price,
            @Field("g_body") String body
    );

    /**
     * 删除茶市图片
     * @param key 登录令牌
     * @return 茶市列表
     */
    @FormUrlEncoded
    @POST("?act=member_flea&op=drop_goods")
    Observable<Integer> fleaImageDel(
            @Field("key") String key,
            @Field("upload_id") int goods_id
    );

    @FormUrlEncoded
    @POST("?act=member_feedback&op=feedback_add")
    Observable<Integer> feedbackAdd(
            @Field("key") String key,
            @Field("feedback") String feedback,
            @Field("contact") String contact
    );

    @GET("?act=index&op=apk_version")
    Observable<UpdateInfo> checkUpdate(
            @Query("version") String version
    );

}
