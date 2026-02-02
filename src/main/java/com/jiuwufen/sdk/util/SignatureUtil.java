package com.jiuwufen.sdk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 签名工具类
 * 
 * <p>提供签名生成、验证和地址解密功能
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
public class SignatureUtil {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    /**
     * 生成请求签名
     * 
     * <p>算法: token = md5(base64_encode(商家密钥 + 平台密钥) + 排序并拼接后的参数字符串)
     * 
     * @param params 请求参数
     * @param merchantSecret 商家密钥
     * @param platformSecret 平台密钥
     * @return 签名字符串
     */
    public static String generateSignature(Map<String, Object> params, 
                                          String merchantSecret, 
                                          String platformSecret) {
        try {
            // 1. 获取所有 Keys 并排序（排除 token 本身）
            List<String> keys = new ArrayList<>();
            for (String key : params.keySet()) {
                if (!"token".equals(key)) {
                    keys.add(key);
                }
            }
            Collections.sort(keys);
            
            // 2. 拼接参数值
            StringBuilder paramsStr = new StringBuilder();
            for (String key : keys) {
                Object value = params.get(key);
                if (value instanceof String) {
                    paramsStr.append(value);
                } else if (value != null) {
                    // 复杂类型转 JSON（需要递归排序 Key）
                    String json = GSON.toJson(sortMapKeys(value));
                    paramsStr.append(json);
                }
            }
            
            // 3. Base64 编码密钥
            String secret = merchantSecret + platformSecret;
            String base64Secret = Base64.getEncoder()
                .encodeToString(secret.getBytes(StandardCharsets.UTF_8));
            
            // 4. 拼接并计算 MD5
            String finalStr = base64Secret + paramsStr.toString();
            return md5(finalStr);
            
        } catch (Exception e) {
            throw new RuntimeException("Generate signature failed", e);
        }
    }
    
    /**
     * 验证签名
     * 
     * @param params 请求参数
     * @param expectedToken 期望的签名
     * @param merchantSecret 商家密钥
     * @param platformSecret 平台密钥
     * @return 是否验证通过
     */
    public static boolean verifySignature(Map<String, Object> params,
                                         String expectedToken,
                                         String merchantSecret,
                                         String platformSecret) {
        String actualToken = generateSignature(params, merchantSecret, platformSecret);
        return actualToken.equals(expectedToken);
    }
    
    /**
     * 解密地址
     * 
     * <p>使用 AES-ECB 模式解密地址字符串
     * 
     * @param cipherText 密文（Base64 编码）
     * @param key 密钥
     * @return 明文地址
     */
    public static String decryptAddress(String cipherText, byte[] key) {
        try {
            // Base64 解码密文
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            
            // 创建 AES cipher
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // 解密
            byte[] plainBytes = cipher.doFinal(cipherBytes);
            return new String(plainBytes, StandardCharsets.UTF_8);
            
        } catch (Exception e) {
            throw new RuntimeException("Decrypt address failed", e);
        }
    }
    
    /**
     * 加密地址（用于测试）
     * 
     * @param plainText 明文
     * @param key 密钥
     * @return 密文（Base64 编码）
     */
    public static String encryptAddress(String plainText, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherBytes);
            
        } catch (Exception e) {
            throw new RuntimeException("Encrypt address failed", e);
        }
    }
    
    /**
     * 计算 MD5
     */
    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("MD5 calculation failed", e);
        }
    }
    
    /**
     * 递归排序 Map 的 Keys
     */
    @SuppressWarnings("unchecked")
    private static Object sortMapKeys(Object obj) {
        if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            Map<String, Object> sortedMap = new TreeMap<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sortedMap.put(entry.getKey(), sortMapKeys(entry.getValue()));
            }
            return sortedMap;
        } else if (obj instanceof List) {
            List<Object> list = (List<Object>) obj;
            List<Object> sortedList = new ArrayList<>();
            for (Object item : list) {
                sortedList.add(sortMapKeys(item));
            }
            return sortedList;
        }
        return obj;
    }
}
