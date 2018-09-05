package com.yangy.common.utils;

import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * 请求工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/8
 * @since 1.0.0
 */
public class HttpUtil {

    public static void main(String[] args) {

        String post = null;
        try {
//            User user = new User();
//            user.setUserId(180L);
//            String domain = "https://luyanan.com";
//            String url = "/gateway/user/dict/find/key";
//
//            Map<String, Object> stringMapHashMap = new HashMap<>();
//            stringMapHashMap.put("msg", "product_type");
//            String toString = getMapToString(stringMapHashMap, true);
//            post = get(domain, url, toString, 1000, 1000);
//            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String contentType, String domain, String urlSuffix, String data, int readTimeoutMs, int connectTimeoutMs) throws Exception {
        HttpClient httpClient = getHttpClient();
        String url = domain + urlSuffix;
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);
        switch (contentType) {
            case "application/x-www-form-urlencoded":
                url = url + data;
                httpPost.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
                break;
            case "application/json":
                httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
                break;
            case "application/mybatis":
                httpPost.addHeader("Content-Type", ContentType.APPLICATION_XML.getMimeType());
                break;
        }

        StringEntity postEntity = new StringEntity(data, Consts.UTF_8.name());
        httpPost.setEntity(postEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        System.out.println("请求地址 -> " + url);
        return EntityUtils.toString(httpEntity, Consts.UTF_8.name());
    }

    public static String get(String domain, String urlSuffix, String data, int readTimeoutMs, int connectTimeoutMs) throws Exception {
        HttpClient httpClient = getHttpClient();
        String url = domain + urlSuffix + data;
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();

        System.out.println("请求地址 -> " + url);
        return EntityUtils.toString(httpEntity, Consts.UTF_8.name());

    }


    public static String getMapToString(Map<String, Object> map, boolean flag) {
        if (null == map || 0 == map.size()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (flag) {
                stringBuffer.append("/" + value);
            } else {
                if (0 == stringBuffer.length()) {
                    stringBuffer.append("?" + key + "=" + value);
                }
                stringBuffer.append("&" + key + "=" + value);
            }
        }
        return stringBuffer.toString();
    }

    public static HttpClient getHttpClient() {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );

        return HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
    }

}