package com.jiuwufen.sdk.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
