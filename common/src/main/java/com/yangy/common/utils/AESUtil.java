package com.yangy.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES加密工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/6
 * @since 1.0.0
 */
public class AESUtil {

    private static final String ENCODE_RULES = "YANG";
    private static final String AES = "AES";

    public static String aesEncode(String info) {
        try {
            //1.获取秘钥生成器实例 指定为 AES(aes) 不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            //2.根据ENCODE_RULES规则 初始化秘钥生成器
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //根据传入的字节数组 生成一个128位的随机源
            secureRandom.setSeed(ENCODE_RULES.getBytes());
            keyGenerator.init(128, secureRandom);
            //3.生成原始对称秘钥
            SecretKey originalKey = keyGenerator.generateKey();
            //4.获取原始对称秘钥的字节数组
            byte[] originalKeyEncoded = originalKey.getEncoded();
            //5.根据字节数组生成AES秘钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(originalKeyEncoded, AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(AES);
            //7.初始化密码器 参数 #1 操作类型(加解密) #2 使用的key
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] bytes = info.getBytes("UTF-8");
            byte[] bytesAES = cipher.doFinal(bytes);
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(bytesAES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesDecode(String info) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance(AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(ENCODE_RULES.getBytes());
            keygen.init(128, random);
            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(AES);
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decode = decoder.decode(info);
            return new String(cipher.doFinal(decode), "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String info = "this ia a message";

        String aesEncode = aesEncode(info);
        System.out.println("加密结果: " + aesEncode);
        String aesDecode = aesDecode(aesEncode);
        System.out.println("解密结果: " + aesDecode);


    }

}