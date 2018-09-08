package com.yangy.common.config;


import org.springframework.beans.factory.annotation.Value;

/**
 * 微信相关的配置文件信息
 *
 * @author yang yang
 * @create 2018/9/7
 * @since 1.0.0
 */
public class WeChatConfig {

    //appId
    private static String APPID;
    //app secret
    private static String APP_SECRET;

    // 网页授权获取 code
    private static String GET_CODE_URL;
    // 网页授权接口
    private static String GET_OPENID_URL;
    // 获取JS OPENID
    private static String GET_JS_OPENID_URL;
    // 刷新 token
    private static String REFRESH_TOKEN_URL;
    // 网页授权得到用户基本信息接口
    private static String GET_USER_INFO_URL;
    // 验证 token 有效性
    private static String VERIFY_AUTHORIZATION_URL;
    private static String ACCESS_TOKEN_URL;
    private static String QR_CODE_URL;

    private static String SMALL_ROUTINE_HOME = "pages/Home/index";

    @Value("${wechat.appId}")
    public static void setbAppId(String appId) {
        APPID = appId;
    }

    @Value("${wechat.appSecret}")
    public static void setfAppSecret(String appSecret) {
        appSecret = APP_SECRET;
    }

    @Value("${wechat.getCodeUrl}")
    public static void setGetCode(String getCode) {
        GET_CODE_URL = getCode;
    }

    @Value("${wechat.getOpenIdUrl}")
    public static void setGetOpenid(String getOpenid) {
        GET_OPENID_URL = getOpenid;
    }

    @Value("${wechat.getJsOpenIdUrl}")
    public static void setGetJsOpenid(String getJsOpenid) {
        GET_JS_OPENID_URL = getJsOpenid;
    }

    @Value("${wechat.refreshTokenUrl}")
    public static void setRefreshToken(String refreshToken) {
        REFRESH_TOKEN_URL = refreshToken;
    }

    @Value("${wechat.getUserInfoUrl}")
    public static void setGetUserInfo(String getUserInfo) {
        GET_USER_INFO_URL = getUserInfo;
    }

    @Value("${wechat.verifyAuthorizationUrl}")
    public static void setVerifyAuthorization(String verifyAuthorization) {
        VERIFY_AUTHORIZATION_URL = verifyAuthorization;
    }

    @Value("${wechat.accessTokenUrl}")
    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN_URL = accessToken;
    }

    @Value("${wechat.qrCodeUrl}")
    public static void setQrCode(String qrCode) {
        QR_CODE_URL = qrCode;
    }

    public static void setSmallRoutineHome(String smallRoutineHome) {
        SMALL_ROUTINE_HOME = smallRoutineHome;
    }

    public static String getSmallRoutineHome() {
        return SMALL_ROUTINE_HOME;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getAppSecret() {
        return APP_SECRET;
    }

    public static String getGetCodeUrl() {
        return GET_CODE_URL;
    }

    public static String getGetOpenidUrl() {
        return GET_OPENID_URL;
    }

    public static String getGetJsOpenidUrl() {
        return GET_JS_OPENID_URL;
    }

    public static String getRefreshTokenUrl() {
        return REFRESH_TOKEN_URL;
    }

    public static String getGetUserInfoUrl() {
        return GET_USER_INFO_URL;
    }

    public static String getVerifyAuthorizationUrl() {
        return VERIFY_AUTHORIZATION_URL;
    }

    public static String getAccessTokenUrl() {
        return ACCESS_TOKEN_URL;
    }

    public static String getQrCodeUrl() {
        return QR_CODE_URL;
    }

}