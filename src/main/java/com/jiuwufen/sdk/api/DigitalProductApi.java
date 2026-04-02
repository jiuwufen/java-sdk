package com.jiuwufen.sdk.api;

import com.google.gson.JsonObject;
import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.digitalproduct.*;

/**
 * 3C 数码相关 API（质检项、IMEI、一键出售、后验签收、绑扣等）。
 * <p>
 * 一键出售 v1/v2 使用 {@link DigitalSuperSaleRequest}、{@link DigitalSuperSaleV2Request} 及配套嵌套类型，与开放平台文档字段一致。
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
     *
     * @see DigitalSuperSaleRequest
     * @see DigitalSuperSaleV1ExaminingItem
     * @see DigitalSuperSaleV1SecondExaminingItem
     * @see DigitalSuperSaleV1ThirdExaminingItem
     * @see DigitalSuperSaleAddress
     */
    public JsonObject digitalSuperSale(DigitalSuperSaleRequest request) throws ApiException {
        return client.execute("/api_tob/digitalSuperSale/v1.0", request, JsonObject.class);
    }

    /**
     * 一键出售 - 自研商家 (DigitalSuperSale v2.0)
     *
     * @see DigitalSuperSaleV2Request
     * @see DigitalSuperSaleV2ExaminingItem
     * @see DigitalSuperSaleAddress
     */
    public JsonObject digitalSuperSaleV2(DigitalSuperSaleV2Request request) throws ApiException {
        return client.execute("/api_tob/digitalSuperSale/v2.0", request, JsonObject.class);
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
