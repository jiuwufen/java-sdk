package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 一键出售请求中的卖家地址（DigitalSuperSale v1 / v2 共用，与开放平台 JSON 字段一致）。
 */
@Data
public class DigitalSuperSaleAddress {

    @SerializedName("province")
    private String province;

    @SerializedName("city")
    private String city;

    @SerializedName("county")
    private String county;

    @SerializedName("street")
    private String street;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;
}
