package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 一键出售 - 自研商家 (DigitalSuperSale v2.0) 请求体。
 * <p>
 * 与文档中 {@code digital-super-sale-v2} 一致；{@code quality} 为成色名称字符串（如：准新机）。
 */
@Data
public class DigitalSuperSaleV2Request {

    @SerializedName("goods_sn")
    private String goodsSn;

    @SerializedName("sku_id")
    private Long skuId;

    /** 成色名称（如: 准新机） */
    @SerializedName("quality")
    private String quality;

    @SerializedName("address")
    private DigitalSuperSaleAddress address;

    @SerializedName("imei")
    private String imei;

    @SerializedName("price")
    private Integer price;

    @SerializedName("general_imgs")
    private List<String> generalImgs;

    /** 选填 */
    @SerializedName("flaw_imgs")
    private List<String> flawImgs;

    @SerializedName("examining_list")
    private List<DigitalSuperSaleV2ExaminingItem> examiningList;

    @SerializedName("result_desc")
    private String resultDesc;
}
