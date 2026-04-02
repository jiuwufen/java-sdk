package com.jiuwufen.sdk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
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
            // Gson/objectToMap 会把 JSON 数字变成 Double，toJson 会得到 12.0；与 Go json.Marshal 的 12 不一致，签名会错
            normalizeIntegralNumbersInPlace(params);

            // 1. 获取所有 Keys 并排序（排除 token 本身）
            List<String> keys = new ArrayList<>();
            for (String key : params.keySet()) {
                if (!"token".equals(key)) {
                    keys.add(key);
                }
            }
            Collections.sort(keys);
            
            // 2. 拼接参数值（与 Go GenSign.addRawParams + GetSign 一致：仅 string 原样拼接，其余走 JSON；nil 为字面量 null）
            StringBuilder paramsStr = new StringBuilder();
            for (String key : keys) {
                Object value = params.get(key);
                if (value instanceof String) {
                    paramsStr.append(value);
                } else {
                    // 与 Go json.Marshal(sortMapKeys(v)) 等价：嵌套 map 按 key 排序后再序列化（Gson 对 Map 插入序敏感）
                    paramsStr.append(GSON.toJson(sortMapKeys(value)));
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
     * 递归将「数学上为整数」的 {@link Double}/{@link Float} 转为 {@link Long}，与 Go {@code json.Unmarshal} 后再 {@code json.Marshal}
     * 对整数值的序列化习惯对齐，保证签名字符串一致。
     * <p>
     * 会就地修改 {@code params} 及其嵌套的 {@link Map}、{@link List}。
     */
    @SuppressWarnings("unchecked")
    public static void normalizeIntegralNumbersInPlace(Map<String, Object> params) {
        if (params == null) {
            return;
        }
        for (Map.Entry<String, Object> e : params.entrySet()) {
            e.setValue(normalizeIntegralRecursive(e.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    private static Object normalizeIntegralRecursive(Object o) {
        if (o instanceof Map) {
            Map<String, Object> m = (Map<String, Object>) o;
            for (Map.Entry<String, Object> e : m.entrySet()) {
                e.setValue(normalizeIntegralRecursive(e.getValue()));
            }
            return m;
        }
        if (o instanceof List) {
            List<Object> list = (List<Object>) o;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, normalizeIntegralRecursive(list.get(i)));
            }
            return list;
        }
        return normalizeIntegralLeaf(o);
    }

    private static Object normalizeIntegralLeaf(Object o) {
        if (o instanceof Double) {
            double d = (Double) o;
            if (Double.isFinite(d) && d == Math.rint(d) && d >= Long.MIN_VALUE && d <= Long.MAX_VALUE) {
                return (long) d;
            }
            return o;
        }
        if (o instanceof Float) {
            float f = (Float) o;
            if (Float.isFinite(f) && f == Math.rint(f) && f >= Long.MIN_VALUE && f <= Long.MAX_VALUE) {
                return (long) f;
            }
            return o;
        }
        if (o instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) o;
            try {
                return bd.longValueExact();
            } catch (ArithmeticException ignored) {
                return o;
            }
        }
        return o;
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
