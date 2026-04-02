package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 平台商家一键出售 — 质检项二级节点（examining_list[].second_examining_list[]）。
 */
@Data
public class DigitalSuperSaleV1SecondExaminingItem {

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;

    /** 三级质检项列表（选填） */
    @SerializedName("third_examining_list")
    private List<DigitalSuperSaleV1ThirdExaminingItem> thirdExaminingList;
}
