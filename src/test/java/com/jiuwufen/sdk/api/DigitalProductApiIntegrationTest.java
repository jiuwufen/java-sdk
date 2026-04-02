package com.jiuwufen.sdk.api;

import com.google.gson.JsonObject;
import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.digitalproduct.ExaminingConfigRequest;
import com.jiuwufen.sdk.model.digitalproduct.ImeiQueryRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * {@link DigitalProductApi} 集成测试：对开放平台发起真实 HTTP（与 {@link DigitalProductApiTest} 的 Mock 单测互补）。
 * <p>
 * 运行前请在环境中配置：
 * <ul>
 *   <li>{@code FEN95_ERP_NAME}</li>
 *   <li>{@code FEN95_THIRD_PARTY_ID}</li>
 *   <li>{@code FEN95_MERCHANT_SECRET}</li>
 *   <li>{@code FEN95_PLATFORM_SECRET}</li>
 *   <li>{@code FEN95_TEST_IMEI} — 要查询的 IMEI</li>
 *   <li>{@code FEN95_BASE_URL} — 可选，默认 {@code http://d1.95fenapp.com}</li>
 * </ul>
 */
class DigitalProductApiIntegrationTest {

    private static String env(String name) {
        String v = System.getenv(name);
        return (v == null || v.isBlank()) ? null : v.trim();
    }

    @Test
    void imeiQuery_realHttp() throws ApiException {
//        assumeTrue(env("FEN95_ERP_NAME") != null, "未设置 FEN95_ERP_NAME，跳过真实请求");
//        assumeTrue(env("FEN95_THIRD_PARTY_ID") != null, "未设置 FEN95_THIRD_PARTY_ID，跳过真实请求");
//        assumeTrue(env("FEN95_MERCHANT_SECRET") != null, "未设置 FEN95_MERCHANT_SECRET，跳过真实请求");
//        assumeTrue(env("FEN95_PLATFORM_SECRET") != null, "未设置 FEN95_PLATFORM_SECRET，跳过真实请求");
//        assumeTrue(env("FEN95_TEST_IMEI") != null, "未设置 FEN95_TEST_IMEI，跳过真实请求");

        String baseUrl = env("FEN95_BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://t1.95fenapp.com";
        }

        JiuWuFenClient client = JiuWuFenClient.builder()
                .erpName("shanhuiyoupin")
                .thirdPartyId("118735599661219962")
                .merchantSecret("F2docExmicC7Gee4B8mR3R93WNSSqCnS")
                .platformSecret("5a9550b33d4c66c8d9085de581066bed")
                .baseUrl(baseUrl)
                .debug(true)
                .build();

        ImeiQueryRequest request = new ImeiQueryRequest();
        request.setImei("111111");

        JsonObject data = client.digitalProduct().imeiQuery(request);

        assertNotNull(data, "业务 data 不应为 null");
        assertTrue(data.has("status"), "IMEI 查询成功时应返回 data.status（1: 已上架, 2: 未上架）");
        int status = data.get("status").getAsInt();
        assertTrue(status == 1 || status == 2, "status 应为 1 或 2，实际: " + status);
    }

    @Test
    void examiningConfig_realHttp() throws ApiException {
//        assumeTrue(env("FEN95_ERP_NAME") != null, "未设置 FEN95_ERP_NAME，跳过真实请求");
//        assumeTrue(env("FEN95_THIRD_PARTY_ID") != null, "未设置 FEN95_THIRD_PARTY_ID，跳过真实请求");
//        assumeTrue(env("FEN95_MERCHANT_SECRET") != null, "未设置 FEN95_MERCHANT_SECRET，跳过真实请求");
//        assumeTrue(env("FEN95_PLATFORM_SECRET") != null, "未设置 FEN95_PLATFORM_SECRET，跳过真实请求");
//        assumeTrue(env("FEN95_TEST_IMEI") != null, "未设置 FEN95_TEST_IMEI，跳过真实请求");

        String baseUrl = env("FEN95_BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://t1.95fenapp.com";
        }

        JiuWuFenClient client = JiuWuFenClient.builder()
                .erpName("shanhuiyoupin")
                .thirdPartyId("118735599661219962")
                .merchantSecret("F2docExmicC7Gee4B8mR3R93WNSSqCnS")
                .platformSecret("5a9550b33d4c66c8d9085de581066bed")
                .baseUrl(baseUrl)
                .debug(true)
                .build();

        ExaminingConfigRequest request = new ExaminingConfigRequest();
        request.setTemplateId(12L);

        JsonObject data = client.digitalProduct().examiningConfig(request);

        assertNotNull(data, "业务 data 不应为 null");
        assertTrue(data.has("status"), "IMEI 查询成功时应返回 data.status（1: 已上架, 2: 未上架）");
        int status = data.get("status").getAsInt();
        assertTrue(status == 1 || status == 2, "status 应为 1 或 2，实际: " + status);
    }
}
