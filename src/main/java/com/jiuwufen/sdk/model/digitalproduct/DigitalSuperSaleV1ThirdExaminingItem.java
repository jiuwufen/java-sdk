package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 平台商家一键出售 — 质检项三级节点（examining_list[].second_examining_list[].third_examining_list[]）。
 */
@Data
public class DigitalSuperSaleV1ThirdExaminingItem {

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;

    /** 质检图片（选填） */
    @SerializedName("imgs")
    private List<String> imgs;

    /** 质检状态（true: 正常, false: 异常） */
    @SerializedName("status")
    private Boolean status;
}
