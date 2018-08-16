package com.yangy.common.utils.login;

import org.apache.http.Consts;

import java.net.URLEncoder;

/**
 * 微信第三方登录工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/8
 * @since 1.0.0
 */
public class WeChatLoginUtil {

    public static void getCode() throws Exception {
        String encode = URLEncoder.encode("http://140.143.61.41/user/login/notify", Consts.UTF_8.name());

        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

        url = url.replace("APPID", "wxcc5884c547bc6752")
                .replace("REDIRECT_URI", encode)
                .replace("SCOPE", "snsapi_base")
                .replace("STATE", "wechat_sdk_demo")
        ;

        System.out.println(url);

//        String s = HttpUtil.get("https://open.weixin.qq.com", "/connect/qrconnect", url.split("?")[1], 1000, 1000);
//        System.out.println(s);

    }

    public static void main(String[] args) throws Exception {
        getCode();
    }


}