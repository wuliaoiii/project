//package com.yangy.pay.controller;
//
//import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
//import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
//import com.github.binarywang.wxpay.exception.WxPayException;
//import com.github.binarywang.wxpay.service.WxPayService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author yang yang
// * @create 2018/8/20
// * @since 1.0.0
// */
//@RestController
//@RequestMapping("/pay")
//public class PayController {
//
//    @Resource
//    private WxPayService wxPayService;
//
//    /**
//     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
//     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
//     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
//     *
//     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
//     */
//    @PostMapping("/unifiedOrder")
//    public WxPayUnifiedOrderResult unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
//        return wxPayService.unifiedOrder(request);
//    }
//
//}