package com.damenghai.chahuitong.config;

/**
 * Created by Sgun on 15/10/9.
 */
public interface Config {
    String BASE_URL = "http://www.chahuitong.com/";

    String BASE_GOODS_DETAIL_URL = BASE_URL + "wap/index.php/Home/Index/goods?goods_id=";

    String BASE_GOODS_BODY_URL = BASE_URL + "mobile/index.php?act=goods&op=goods_body&goods_id=";

    /** 默认订单列表是否获取支付方式 */
    boolean DEFAULT_GET_PAYMENT = false;

    /** 默认列表页每页显示数量 */
    int DEFAULT_PAGE_SIZE = 20;

    // 这是Bmob的ApplicationId,用于初始化操作
    String applicationId = "c9a6c7e8fe89e14a6458ed592209e96c";

    // shopNc md5
    String MD5_KEY = "804451dc13014b1c785fb73b1617b760";

    // 用于服务端判断客户端类型
    String CLIENT_TYPE = "android";

    // 微信appID、appSecret
    String WX_ID = "wx025bfd51ec3b664a";
    String WX_SECRET ="cdfc0e3a367f44bf4b22e41b4073f274";

    // 微博appID appSecret
    String WB_ID = "848852268";
    String WB_SECRET = "c312c8076d3289a648e7663787cfea86";

    // QQ appID appSecret
    String QQ_ID = "1104563629";
    String QQ_SECRET = "rJbMttJCa47MBsCk";
}
