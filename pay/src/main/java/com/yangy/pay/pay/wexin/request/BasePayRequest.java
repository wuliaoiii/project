package com.yangy.pay.pay.wexin.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信支付请求参数基础类
 *
 * @author yang yang
 * @create 2018/8/20
 * @since 1.0.0
 */
@XStreamAlias("mybatis")
public abstract class BasePayRequest {

    @XStreamAlias("appid")
    private String appId;

    @XStreamAlias("mch_id")
    private String mchId;

    @XStreamAlias("device_info")
    private String deviceInfo;

    @XStreamAlias("nonce_str")
    private String nonceStr;

    @XStreamAlias("sign")
    private String sign;

    @XStreamAlias("sign_type")
    private String signType;

    @XStreamAlias("body")
    private String body;

    @XStreamAlias("detail")
    private String detail;

    @XStreamAlias("attach")
    private String attach;

    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    @XStreamAlias("fee_type")
    private String feeType;

    @XStreamAlias("total_fee")
    private String totalFee;

    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    @XStreamAlias("time_start")
    private String timeStart;

    @XStreamAlias("time_expire")
    private String timeExpire;

    @XStreamAlias("goods_tag")
    private String goodsTag;

    @XStreamAlias("notify_url")
    private String notifyUrl;

    @XStreamAlias("trade_type")
    private String tradeType;

    @XStreamAlias("limit_pay")
    private String limitPay;

    @XStreamAlias("scene_info")
    private String sceneInfo;


//    public String toXML() {
//        XStream instance = XStreamInitializer.getInstance();
//        return instance.toXML(this);
//    }
}