package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 平台商家一键出售 — 质检项一级节点（examining_list[]）。
 */
@Data
public class DigitalSuperSaleV1ExaminingItem {

    /** 一级质检项 Key */
    @SerializedName("key")
    private String key;

    /** 二级质检项列表 */
    @SerializedName("second_examining_list")
    private List<DigitalSuperSaleV1SecondExaminingItem> secondExaminingList;
}
