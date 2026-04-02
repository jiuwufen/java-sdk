package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 一键出售 - 平台商家 (DigitalSuperSale v1.0) 请求体。
 * <p>
 * 字段与开放平台文档及 {@code api-definitions.json} 中 {@code digital-super-sale} 一致，含完整嵌套质检结构。
 */
@Data
public class DigitalSuperSaleRequest {

    @SerializedName("goods_sn")
    private String goodsSn;

    @SerializedName("sku_id")
    private Long skuId;

    /** 成色（数值） */
    @SerializedName("quality")
    private Integer quality;

    @SerializedName("address")
    private DigitalSuperSaleAddress address;

    @SerializedName("imei")
    private String imei;

    /** 售价（分） */
    @SerializedName("price")
    private Integer price;

    /** 实拍图 (5-15张) */
    @SerializedName("general_imgs")
    private List<String> generalImgs;

    /** 瑕疵图 (1-15张)，选填 */
    @SerializedName("flaw_imgs")
    private List<String> flawImgs;

    @SerializedName("examining_list")
    private List<DigitalSuperSaleV1ExaminingItem> examiningList;

    @SerializedName("result_desc")
    private String resultDesc;

    @SerializedName("template_id")
    private Integer templateId;

    /** 选填 */
    @SerializedName("internal_enterprise_id")
    private String internalEnterpriseId;

    /** 货品来源 (1: B端特供)，选填 */
    @SerializedName("product_source_type")
    private Integer productSourceType;
}
