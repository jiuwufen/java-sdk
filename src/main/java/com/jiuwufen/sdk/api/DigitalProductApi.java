package com.jiuwufen.sdk.api;

import com.google.gson.JsonObject;
import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.digitalproduct.*;

import java.util.Map;

/**
 * 3C 数码相关 API（质检项、IMEI、一键出售、后验签收、绑扣等）。
 * <p>
 * 复杂嵌套请求体（如一键出售）请使用 {@link Map} 构造与文档一致的 JSON 结构。
 */
public class DigitalProductApi {

    private final JiuWuFenClient client;

    public DigitalProductApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 质检项查询 (ExaminingConfig)
     */
    public JsonObject examiningConfig(ExaminingConfigRequest request) throws ApiException {
        return client.execute("/api_tob/examiningConfig/v1.0", request, JsonObject.class);
    }

    /**
     * IMEI 查询 (Imei)
     */
    public JsonObject imeiQuery(ImeiQueryRequest request) throws ApiException {
        return client.execute("/api_tob/imei/v1.0", request, JsonObject.class);
    }

    /**
     * 一键出售 - 平台商家 (DigitalSuperSale v1.0)
     */
    public JsonObject digitalSuperSale(Map<String, Object> body) throws ApiException {
        return client.execute("/api_tob/digitalSuperSale/v1.0", body, JsonObject.class);
    }

    /**
     * 一键出售 - 自研商家 (DigitalSuperSale v2.0)
     */
    public JsonObject digitalSuperSaleV2(Map<String, Object> body) throws ApiException {
        return client.execute("/api_tob/digitalSuperSale/v2.0", body, JsonObject.class);
    }

    /**
     * 后验退回签收 (InspectSignReceipt)
     */
    public JsonObject inspectSignReceipt(InspectSignReceiptRequest request) throws ApiException {
        return client.execute("/api_tob/inspectSignReceipt/v1.0", request, JsonObject.class);
    }

    /**
     * 绑扣/更新绑扣 (BindCertificate)
     */
    public JsonObject bindCertificateBuckle(BindCertificateBuckleRequest request) throws ApiException {
        return client.execute("/api_tob/bindCertificateBuckle/v1.0", request, JsonObject.class);
    }
}
