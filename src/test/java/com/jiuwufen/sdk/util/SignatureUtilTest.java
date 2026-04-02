package com.jiuwufen.sdk.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具测试类
 */
class SignatureUtilTest {

    private static final String MERCHANT_SECRET = "merchant_secret_123";
    private static final String PLATFORM_SECRET = "platform_secret_456";

    @Test
    void testGenerateSignature() {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", "13800000000");

        String signature = SignatureUtil.generateSignature(params, MERCHANT_SECRET, PLATFORM_SECRET);

        assertNotNull(signature);
        assertFalse(signature.isEmpty());
        assertEquals(32, signature.length()); // MD5 长度为 32
    }

    @Test
    void testGenerateSignatureWithMultipleParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("b", "2");
        params.put("a", "1");
        params.put("c", "3");

        String signature = SignatureUtil.generateSignature(params, MERCHANT_SECRET, PLATFORM_SECRET);

        assertNotNull(signature);
        assertEquals(32, signature.length());
    }

    @Test
    void testVerifySignature() {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", "13800000000");

        // 生成签名
        String signature = SignatureUtil.generateSignature(params, MERCHANT_SECRET, PLATFORM_SECRET);

        // 验证签名
        boolean valid = SignatureUtil.verifySignature(params, signature, MERCHANT_SECRET, PLATFORM_SECRET);
        assertTrue(valid);

        // 验证错误的签名
        boolean invalid = SignatureUtil.verifySignature(params, "invalid_signature", MERCHANT_SECRET, PLATFORM_SECRET);
        assertFalse(invalid);
    }

    @Test
    void testEncryptDecryptAddress() {
        String plainText = "上海市浦东新区张江高科技园区";
        byte[] key = "12345678901234567890123456789012".getBytes(); // 32字节密钥

        // 加密
        String cipherText = SignatureUtil.encryptAddress(plainText, key);
        assertNotNull(cipherText);
        assertFalse(cipherText.isEmpty());

        // 解密
        String decrypted = SignatureUtil.decryptAddress(cipherText, key);
        assertEquals(plainText, decrypted);
    }

    @Test
    void testGenerateSignatureConsistency() {
        Map<String, Object> params = new HashMap<>();
        params.put("goods_sn", "GOODS-001");
        params.put("price", 29900);

        String signature1 = SignatureUtil.generateSignature(params, MERCHANT_SECRET, PLATFORM_SECRET);
        String signature2 = SignatureUtil.generateSignature(params, MERCHANT_SECRET, PLATFORM_SECRET);

        // 相同参数应该生成相同的签名
        assertEquals(signature1, signature2);
    }

    /**
     * Gson 反序列化后的 Double 整数值（如 12.0）应与 Long/Integer 12 生成相同签名，避免签名字符串里出现 12.0。
     */
    @Test
    void testGenerateSignature_doubleWholeNumberMatchesLong() {
        Map<String, Object> withLong = new HashMap<>();
        withLong.put("imei", "x");
        withLong.put("sku_id", 12L);

        Map<String, Object> withDouble = new HashMap<>();
        withDouble.put("imei", "x");
        withDouble.put("sku_id", 12.0);

        String sigLong = SignatureUtil.generateSignature(withLong, MERCHANT_SECRET, PLATFORM_SECRET);
        String sigDouble = SignatureUtil.generateSignature(withDouble, MERCHANT_SECRET, PLATFORM_SECRET);

        assertEquals(sigLong, sigDouble);
    }

    @Test
    void testGenerateSignature_nestedMapDoubleWholeNumberMatchesLong() {
        Map<String, Object> innerLong = new HashMap<>();
        innerLong.put("qty", 100L);
        Map<String, Object> rootLong = new HashMap<>();
        rootLong.put("detail", Arrays.asList(innerLong));

        Map<String, Object> innerDouble = new HashMap<>();
        innerDouble.put("qty", 100.0);
        Map<String, Object> rootDouble = new HashMap<>();
        rootDouble.put("detail", Arrays.asList(innerDouble));

        assertEquals(
                SignatureUtil.generateSignature(rootLong, MERCHANT_SECRET, PLATFORM_SECRET),
                SignatureUtil.generateSignature(rootDouble, MERCHANT_SECRET, PLATFORM_SECRET));
    }

    /**
     * Go json.Marshal(nil) 为 null；若 Java 跳过 null，签名字符串会少一段，与 GenSign 不一致。
     */
    @Test
    void testGenerateSignature_topLevelNullContributesNullLiteral() {
        Map<String, Object> withNull = new HashMap<>();
        withNull.put("a", null);
        withNull.put("b", "x");

        Map<String, Object> withoutA = new HashMap<>();
        withoutA.put("b", "x");

        String sigWithNull = SignatureUtil.generateSignature(withNull, MERCHANT_SECRET, PLATFORM_SECRET);
        String sigWithout = SignatureUtil.generateSignature(withoutA, MERCHANT_SECRET, PLATFORM_SECRET);

        assertNotEquals(sigWithout, sigWithNull);
    }
}
